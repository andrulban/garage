package andruha_pgs.garage.services.implementations;

import andruha_pgs.garage.exceptions.exceptions.EmptyResponseFromDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityEditOrDeleteIdExistenceInDBException;
import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdExistenceInDBException;
import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.repositories.VehicleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceImplTest {
    @Mock
    private VehicleRepository vehicleRepository;
    private PageRequest pageRequest;
    private VehicleServiceImpl vehicleService;
    private Vehicle vehicle;

    @Before
    public void setUp() {
        vehicleService = new VehicleServiceImpl();
        vehicleService.setVehicleRepository(vehicleRepository);
        pageRequest = new PageRequest(0,20);

        vehicle = new Vehicle();
        vehicle.setNumberPlate("AA32823");
    }

    @Test(expected = EmptyResponseFromDBException.class)
    public void emptyResponseFromEmptyDB() {
        when(vehicleRepository.findAll(pageRequest)).thenReturn(new PageImpl<Vehicle>(Collections.EMPTY_LIST));
        vehicleService.getAll(pageRequest);
    }


    @Test(expected = EmptyResponseFromDBException.class)
    public void wrongBrandEmptyResponse() {
        when(vehicleRepository.getVehiclesByBrand(vehicle.getBrand() ,pageRequest)).thenReturn(new PageImpl<Vehicle>(Collections.EMPTY_LIST));
        vehicleService.getVehiclesByBrand(vehicle.getBrand(), pageRequest);
    }

    @Test(expected = EmptyResponseFromDBException.class)
    public void wrongBodyTypeEmptyResponse() {
        when(vehicleRepository.getVehiclesByBodyType(vehicle.getBodyType() ,pageRequest)).thenReturn(new PageImpl<Vehicle>(Collections.EMPTY_LIST));
        vehicleService.getVehiclesByBodyType(vehicle.getBodyType(), pageRequest);
    }

    @Test(expected = EmptyResponseFromDBException.class)
    public void wrongNumberPlateEmptyResponse() {
        when(vehicleRepository.findOne(vehicle.getNumberPlate())).thenReturn(null);
        vehicleService.getVehicleByNumberPlate(vehicle.getNumberPlate());
    }

    @Test(expected = EntityInsertIdExistenceInDBException.class)
    public void insertEntityInsertIdExistenceInDBException() {
        when(vehicleRepository.findOne(vehicle.getNumberPlate())).thenReturn(new Vehicle());
        vehicleService.addVehicle(vehicle);
    }

    @Test(expected = EntityEditOrDeleteIdExistenceInDBException.class)
    public void editEntityEditOrDeleteIdExistenceInDBException() {
        when(vehicleRepository.findOne(vehicle.getNumberPlate())).thenReturn(null);
        vehicleService.editVehicle(vehicle);
    }

    @Test(expected = EntityEditOrDeleteIdExistenceInDBException.class)
    public void deleteEntityEditOrDeleteIdExistenceInDBException() {
        when(vehicleRepository.findOne(vehicle.getNumberPlate())).thenReturn(null);
        vehicleService.deleteVehicle(vehicle.getNumberPlate());
    }
}
