package vttp.project.keefe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp.project.keefe.model.Accounts;
import vttp.project.keefe.services.AccountsService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountsServiceTest {
    
    @Autowired
    private AccountsService acctSvc;

    @AfterAll
	public void removeInsert(){
		Accounts acct = new Accounts();
        acct.setEmail("abc@test.com");
        acct.setPassword("tester123");
        acct.setSite("ABCWORLD NEWS");
        acct.setUserID(Long.valueOf(1));

        Boolean delAcct = acctSvc.delAcct(acct);
        assertTrue(delAcct);
	}

    @Test
    void testLoadAccounts(){
        Accounts acct = new Accounts();
        acct.setEmail("abc@test.com");
        acct.setPassword("tester123");
        acct.setSite("ABCWORLD NEWS");
        acct.setUserID(Long.valueOf(1));

        Boolean saveAcct = acctSvc.addAccount(acct);

        assertTrue(saveAcct);
    }

    @Test
    void testGetAccounts(){
        int id = 1;
        boolean result;
        List<Accounts> listOfAcc = acctSvc.getAccounts(Long.valueOf(id));
        if(listOfAcc.size() > 1)
            result = true;
        else
            result = false;
        assertTrue(result);
    }

    @Test
    void testLoadAccountFail(){
        Accounts acct = new Accounts();
        acct.setEmail("abc@test.com");
        acct.setPassword("");
        acct.setSite("ABCWORLD");
        acct.setUserID(Long.valueOf(1));

        Boolean saveAcct = acctSvc.addAccount(acct);

        assertFalse(saveAcct);
    }

}
