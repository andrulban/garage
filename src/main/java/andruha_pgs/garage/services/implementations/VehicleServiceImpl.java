package andruha_pgs.garage.services.implementations;

import andruha_pgs.garage.exceptions.exceptions.EmptyResponseFromDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityEditOrDeleteIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdExistenceInDBException;
import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.repositories.VehicleRepository;
import andruha_pgs.garage.services.interfaces.VehicleService;
import com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    private static final Logger LOGGER = LogManager.getLogger(VehicleServiceImpl.class);

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAll(Pageable pageable) throws EmptyResponseFromDBException{
        LOGGER.debug("Calls repository to findAll");
        List<Vehicle> vehicles = vehicleRepository.findAll(pageable).getContent();
        if (!vehicles.isEmpty()) {
            return vehicles;
        } else {
            throw new EmptyResponseFromDBException();
        }
    }

    @Override
    public List<Vehicle> getVehiclesByBrand(String brand, Pageable pageable) throws EmptyResponseFromDBException{
        LOGGER.debug("Calls repository to findByBrand, brand {}", brand);
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByBrand(brand, pageable).getContent();
        if (!vehicles.isEmpty()) {
            return vehicles;
        } else {
            throw new EmptyResponseFromDBException(Collections.singleton("brand:"+brand));
        }
    }

    @Override
    public List<Vehicle> getVehiclesByBodyType(BodyType bodyType, Pageable pageable) throws EmptyResponseFromDBException {
        LOGGER.debug("Calls repository to findByBodyType, bodyType {}", bodyType);
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByBodyType(bodyType, pageable).getContent();
        if (!vehicles.isEmpty()) {
            return vehicles;
        } else {
            throw new EmptyResponseFromDBException(Collections.singleton("bodyType:"+bodyType));
        }
    }

    @Override
    public Vehicle getVehicleByNumberPlate(String numberPlate) throws EmptyResponseFromDBException {
        LOGGER.debug("Calls repository to findByNumberPlate, numberPlate {}", numberPlate);
        Vehicle vehicle = vehicleRepository.getVehicleByNumberPlate(numberPlate);
        if (vehicle!=null) {
            return vehicle;
        } else {
            throw new EmptyResponseFromDBException(Collections.singleton("numberPlate:"+numberPlate));
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws EntityInsertIdExistenceInDBException {
        if (!isVehicleIdInDB(vehicle.getNumberPlate())) {
            LOGGER.debug("Calls repository to insert");
            vehicleRepository.save(vehicle);
            LOGGER.debug("{} with numberPlates {} has been saved", vehicle.getClass(), vehicle.getNumberPlate());
        }
        else {
            throw new EntityInsertIdExistenceInDBException(vehicle.getNumberPlate());
        }
    }

    @Override
    public void editVehicle(Vehicle vehicle) throws EntityEditOrDeleteIdExistenceInDBException {
        if (isVehicleIdInDB(vehicle.getNumberPlate())) {
            LOGGER.debug("Calls repository to update");
            vehicleRepository.saveAndFlush(vehicle);
            LOGGER.debug("{} with numberPlates {} has been updated", vehicle.getClass(), vehicle.getNumberPlate());
        }
        else {
            throw new EntityEditOrDeleteIdExistenceInDBException(vehicle.getNumberPlate());
        }

    }

    @Override
    public void deleteVehicle(String numberPlates) throws EntityEditOrDeleteIdExistenceInDBException {
        if (isVehicleIdInDB(numberPlates)) {
            LOGGER.debug("Calls repository to delete");
            vehicleRepository.delete(numberPlates);
            LOGGER.debug("{} with numberPlates {} has been updated", Vehicle.class, numberPlates);
        }
        else {
            throw new EntityInsertIdExistenceInDBException(numberPlates);
        }

    }

    private boolean isVehicleIdInDB(String numberPlates) {
        return vehicleRepository.findOne(numberPlates) != null;
    }
}
