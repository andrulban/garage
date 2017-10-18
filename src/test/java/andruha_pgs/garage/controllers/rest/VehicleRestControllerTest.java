package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.Vehicle;
import andruha_pgs.garage.models.enums.BodyType;
import andruha_pgs.garage.models.enums.Color;
import andruha_pgs.garage.models.enums.FuelType;
import andruha_pgs.garage.utils.user_permission.annotaions.CustomUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@CustomUser
public class VehicleRestControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;
    private Vehicle vehicle;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        vehicle = new Vehicle();
        vehicle.setNumberPlate("RZ32823");
        vehicle.setWeightKg(1500);
        vehicle.setBrand("Subaru");
        vehicle.setBrandLine("Forester");
        vehicle.setYear(2007);
        vehicle.setFuelType(FuelType.GASOLINE);
        vehicle.setEngineCapacityCm3(2500);
        vehicle.setMileage(140000);
        vehicle.setColor(Color.RED);
        vehicle.setBodyType(BodyType.OFFROAD_4X4);
    }

    @Test
    public void userCanGetVehicle() throws Exception {
        mvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void userCanNotPostVehicle() throws Exception {
        mvc.perform(post("/vehicles")
                .content(objectMapper.writeValueAsString(vehicle))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCanNotPutVehicle() throws Exception {
        mvc.perform(put("/vehicles/RZ32823")
                .content(objectMapper.writeValueAsString(vehicle))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCanNotDeleteVehicle() throws Exception {
        mvc.perform(delete("/vehicles/RZ32823"))
                .andExpect(status().isForbidden());
    }
}
