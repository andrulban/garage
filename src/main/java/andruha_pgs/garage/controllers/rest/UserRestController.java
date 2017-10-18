package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.User;
import andruha_pgs.garage.services.implementations.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRestController {
    private static final Logger LOGGER = LogManager.getLogger(UserRestController.class);
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody User user, BindingResult result) {
        LOGGER.debug("{}, method POST, url /sign-up",this.getClass().toString());
        userService.addUser(user);
        LOGGER.debug("{} has been created.", user.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
