package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.Owner;
import andruha_pgs.garage.services.interfaces.OwnerService;
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
public class OwnerRestController {
    private OwnerService ownerService;
    private static final Logger LOGGER = LogManager.getLogger(OwnerRestController.class);

    @Autowired
    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @JsonView(value = {View.Owner.class})
    @GetMapping(value = "/owners")
    public ResponseEntity<List<Owner>> getAll(Pageable pageable) {
        LOGGER.debug("method GET, url /owners");
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getAll(pageable));
    }

    @JsonView(value = {View.Owner.class})
    @GetMapping(value = "/owners/full-name/{fullName}")
    public ResponseEntity<List<Owner>> getByFullName(@PathVariable String fullName, Pageable pageable) {
        LOGGER.debug("method GET, url /owners/full-name/{}", fullName);
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getOwnersByFullName(fullName, pageable));
    }

    @JsonView(value = {View.Owner.class})
    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<Owner> getById(@PathVariable Long id, Pageable pageable) {
        LOGGER.debug("method GET, url /owners/{}", id);
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.getOwnerById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/owners", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addOwner(@Valid @JsonView(value = {View.Owner.class}) @RequestBody Owner owner) {
        LOGGER.debug("method POST, url /owners");
        ownerService.addOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/owners/" + owner.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/owners/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editOwner(@Valid @JsonView(value = {View.Owner.class}) @RequestBody Owner owner, @PathVariable Long id) {
        LOGGER.debug("method PUT, url /owners/{}", id);
        owner.setId(id);
        ownerService.editOwner(owner);
        return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/owners/" + owner.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        LOGGER.debug("method DELETE, url /owners/{}", id);
        ownerService.deleteOwner(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
