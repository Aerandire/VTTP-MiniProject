package vttp.project.keefe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.project.keefe.model.Accounts;
import vttp.project.keefe.repositories.AccountsRepository;

@Service
public class AccountsService {
    
    @Autowired
    private AccountsRepository accRepo;

    public Boolean addAccount(Accounts acct){
        
        boolean result = accRepo.save(acct);
        if(result != true)
            return false;
        else
            return true;
            
    }

    public  List<Accounts> getAccounts(Long id){
        return accRepo.loadAccountsByID(id);
    }

    public boolean delAcct(Accounts acct){
        return accRepo.delete(acct);
    }
}
