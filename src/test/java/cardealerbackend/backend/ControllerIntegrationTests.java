package cardealerbackend.backend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTests {
    //This file tests the primary Book CRUD and all of the GET methods for the tables.
    @Autowired
    public MockMvc mockMvc;

    @Test
    public void testGetMethodsForAll() throws Exception {
        //This tests the Get Endpoints for all tables with hardcoded entries for the database
        //So the user can test it without DB tool or Postman
        //These are placeholder values for url and containsString

        this.mockMvc.perform(get("/api/inventory/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("111")));
        this.mockMvc.perform(get("/api/cars/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ford"))).andExpect(content().string(containsString("Escape")));


    }
}
