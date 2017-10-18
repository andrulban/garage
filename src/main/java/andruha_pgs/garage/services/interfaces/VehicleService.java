package andruha_pgs.garage.services.interfaces;

import andruha_pgs.garage.models.entities.Vehicle;
import com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll(Pageable pageable);
    List<Vehicle> getVehiclesByBrand(String brand, Pageable pageable);
    List<Vehicle> getVehiclesByBodyType(BodyType bodyType, Pageable pageable);
    Vehicle getVehicleByNumberPlate(String numberPlate);
    void addVehicle(Vehicle vehicle);
    void editVehicle(Vehicle vehicle);
    void deleteVehicle(String numberPlates);
}
