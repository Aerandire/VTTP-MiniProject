package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import vttp.project.keefe.model.User;
import vttp.project.keefe.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private UserService uSvc;

    @Test
    void TestGetIndex() throws Exception{
        MvcResult rs = this.mockMVC.perform(MockMvcRequestBuilders.get("/")).andReturn();
        String viewName = rs.getModelAndView().getViewName();

        assertTrue(viewName.equals("index"));
    }

    @Test
    void TestGetLogin() throws Exception{
        this.mockMVC.perform(MockMvcRequestBuilders.get("/login")).andReturn();
    }

    @Test
    void TestGetRegister() throws Exception{
        this.mockMVC.perform(MockMvcRequestBuilders.get("/register")).andReturn();
    }

    @Test
    void TestPostRegister() throws Exception{
        User user = new User();
        user.setEmail("dfeee@test.com");
        user.setName("abc");
        user.setPassword("tester123");

        boolean result = true;
        Mockito.when(uSvc.saveUser(user)).thenReturn(result);

        this.mockMVC.perform(MockMvcRequestBuilders.post("/process_register")).andReturn();
 
        
    }

    @Test
    void TestGetDashboard() throws Exception{
        this.mockMVC.perform(MockMvcRequestBuilders.get("/dashboard")).andReturn();
    }

    @Test
    void TestPostDashboard() throws Exception{
        this.mockMVC.perform(MockMvcRequestBuilders.post("/dashboard")).andReturn();
    }

    @Test
    void TestGetChecker() throws Exception{
        this.mockMVC.perform(MockMvcRequestBuilders.get("/checker")).andReturn();
    }
}
