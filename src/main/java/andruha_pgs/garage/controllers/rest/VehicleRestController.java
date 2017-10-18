package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.services.interfaces.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleRestController {
    private VehicleService vehicleService;
    private static final Logger LOGGER = LogManager.getLogger(VehicleRestController.class);

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> getAll(Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles");
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAll(pageable));
    }

    @GetMapping(value = "/vehicles/brand/{brand}")
    public ResponseEntity<List<Vehicle>> getByBrand(@PathVariable String brand, Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles/brand/{}", brand);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehiclesByBrand(brand, pageable));
    }
}
