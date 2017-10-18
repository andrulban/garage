package andruha_pgs.garage.repositories;

import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.models.enums.BodyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Page<Vehicle> findAll(Pageable pageable);
    Page<Vehicle> getVehiclesByBodyType(BodyType bodyType, Pageable pageable);
    Page<Vehicle> getVehiclesByBrand(String  brand, Pageable pageable);
    Vehicle getVehicleByNumberPlate(String numberPlates);
}
