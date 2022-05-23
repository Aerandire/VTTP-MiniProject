package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp.project.keefe.model.User;
import vttp.project.keefe.services.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    
    @Autowired
    private UserService uSvc;

    @Test
    void testLoadUserByEmail(){
        String userEmail = "bob@email.com";
        User uu = uSvc.loadUserByEmail(userEmail);
        String name = uu.getName();

        assertEquals("Bobby",name);
    }
    

    
}
