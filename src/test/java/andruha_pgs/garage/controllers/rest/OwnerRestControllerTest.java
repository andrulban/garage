package andruha_pgs.garage.controllers.rest;

import andruha_pgs.garage.models.entities.Owner;
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

import java.util.Date;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@CustomUser
public class OwnerRestControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mvc;
    private Owner owner;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        owner = new Owner();
        owner.setFullName("Custom Name");
        owner.setDateOfBirth(new Date());
    }

    @Test
    public void userCanGetMovie() throws Exception {
        mvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void userCanNotPostMovie() throws Exception {
        mvc.perform(post("/owners")
                .content(objectMapper.writeValueAsString(owner))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCanNotPutMovie() throws Exception {
        mvc.perform(put("/owners/1")
                .content(objectMapper.writeValueAsString(owner))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden());
    }

    @Test
    public void userCanNotDeleteMovie() throws Exception {
        mvc.perform(delete("/owners/1"))
                .andExpect(status().isForbidden());
    }
}
