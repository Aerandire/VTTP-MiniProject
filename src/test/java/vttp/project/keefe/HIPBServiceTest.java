package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp.project.keefe.model.Pwned;
import vttp.project.keefe.services.HIPBService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HIPBServiceTest {
    
    @Autowired
	private HIPBService hSvc;

    @Test
	void testChecker(){
		String email = "abc@abc.com";
		Pwned pwnDomains = hSvc.getResult(email);
		
		assertNotNull(pwnDomains.name);
	}

    
}
