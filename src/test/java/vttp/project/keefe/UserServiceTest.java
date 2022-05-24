package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp.project.keefe.model.User;
import vttp.project.keefe.services.UserService;
import static vttp.project.keefe.repositories.Queries.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    
    @Autowired
    private UserService uSvc;
    
    @Autowired
    private JdbcTemplate template;

    @AfterAll
	public void removeInsert(){
		template.update(SQL_DELETE_USER,"dfe@test.com");
	}

    @Test
    void testLoadUserByEmail(){
        String userEmail = "bob@email.com";
        User uu = uSvc.loadUserByEmail(userEmail);
        String name = uu.getName();

        assertEquals("Bobby",name);
    }

    
    @Test
    public void testCreateUser(){
        User user = new User();

        user.setEmail("dfe@test.com");
        user.setName("abc");
        user.setPassword("tester123");

        boolean savedUser = uSvc.saveUser(user);

        assertTrue(savedUser);
    }

    
}
