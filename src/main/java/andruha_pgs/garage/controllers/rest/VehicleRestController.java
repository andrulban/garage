package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.models.enums.BodyType;
import andruha_pgs.garage.services.interfaces.VehicleService;
import andruha_pgs.garage.views.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class VehicleRestController {
    private VehicleService vehicleService;
    private static final Logger LOGGER = LogManager.getLogger(VehicleRestController.class);

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @JsonView(value = {View.Vehicle.class})
    @GetMapping(value = "/vehicles")
    public ResponseEntity<List<Vehicle>> getAll(Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles");
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getAll(pageable));
    }

    @JsonView(value = {View.Vehicle.class})
    @GetMapping(value = "/vehicles/brand/{brand}")
    public ResponseEntity<List<Vehicle>> getByBrand(@PathVariable String brand, Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles/brand/{}", brand);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehiclesByBrand(brand, pageable));
    }

    @JsonView(value = {View.Vehicle.class})
    @GetMapping(value = "/vehicles/body-type/{bodyType}")
    public ResponseEntity<List<Vehicle>> getByVehicleBodyType(@PathVariable BodyType bodyType, Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles/body-type/{bodyType}", bodyType);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehiclesByBodyType(bodyType, pageable));
    }

    @JsonView(value = {View.Vehicle.class})
    @GetMapping(value = "/vehicles/{numberPlate}")
    public ResponseEntity<Vehicle> getByNumberPlate(@PathVariable String numberPlate, Pageable pageable) {
        LOGGER.debug("method GET, url /vehicles/{}", numberPlate.toUpperCase());
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.getVehicleByNumberPlate(numberPlate.toUpperCase()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addVehicle(@Valid @JsonView(value = {View.Vehicle.class}) @RequestBody Vehicle vehicle) {
        LOGGER.debug("method POST, url /vehicles");
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/vehicles/" + vehicle.getNumberPlate())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/vehicles/{numberPlate}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editVehicle(@Valid @JsonView(value = {View.Vehicle.class}) @RequestBody Vehicle vehicle, @PathVariable String  numberPlate) {
        LOGGER.debug("method PUT, url /vehicles/{}", numberPlate.toUpperCase());
        vehicle.setNumberPlate(numberPlate.toUpperCase());
        vehicleService.editVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/vehicles/" + vehicle.getNumberPlate())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/vehicles/{numberPlate}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String  numberPlate) {
        LOGGER.debug("method DELETE, url /vehicles/{}", numberPlate.toUpperCase());
        vehicleService.deleteVehicle(numberPlate.toUpperCase());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
