package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp.project.keefe.model.CustomUserDetails;
import vttp.project.keefe.model.User;
import vttp.project.keefe.repositories.UserRepository;
import static vttp.project.keefe.repositories.Queries.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTests {
    
    @Autowired
    private UserRepository uRepo;

    @Autowired
    private JdbcTemplate template;

    @AfterAll
	public void removeInsert(){
		template.update(SQL_DELETE_USER,"abc@test.com");
	}

    @Test
    public void testCreateUser(){
        User user = new User();

        user.setEmail("abc@test.com");
        user.setName("abc");
        user.setPassword("tester123");

        boolean savedUser = uRepo.save(user);

        assertTrue(savedUser);
    }

    @Test
    void TestGetDashboard() throws Exception{
        User user = new User();
        int id = 1;
        user.setId(Long.valueOf(id));
        user.setEmail("bob@email.com");
        user.setName("abc");
        user.setPassword("tester123");
        CustomUserDetails uDetails = new CustomUserDetails(user);

        String userN = uDetails.getUsername();
        String N = uDetails.getName();
        Long i = uDetails.getID();
        
        assertNotNull(i);
        assertTrue(userN.equals("bob@email.com"));
        assertTrue(N.equals("abc"));
    }


}
