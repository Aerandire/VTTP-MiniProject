package vttp.project.keefe.repositories;

import static vttp.project.keefe.repositories.Queries.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.project.keefe.AES;
import vttp.project.keefe.model.Accounts;

@Repository
public class AccountsRepository {
    
    @Autowired
    private JdbcTemplate template;

    public Boolean save(Accounts acct) {
        if(acct.getPassword().isEmpty() || acct.getEmail().isEmpty() || acct.getSite().isEmpty())
            return false;
        String encryptedPw = AES.encrypt(acct.getPassword());
        int count = template.update(SQL_INSERT_ACCOUNT, acct.getEmail(), acct.getSite(),encryptedPw, acct.getUserID());

        if(count != 1){
            return false;
        }

        return true;
    }

    public Boolean delete(Accounts acct){
        int count = template.update(SQL_DELETE_ACCT, acct.getSite(), acct.getUserID());

        if(count != 1){
            return false;
        }

        return true;
    }

    public List<Accounts> loadAccountsByID(Long id){
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ACCTS,id);
        List<Accounts> accts = new ArrayList<>();
        
        while (rs.next()){
            Accounts acct = new Accounts();
            acct.setUserID(rs.getLong("user_id"));
            acct.setEmail(rs.getString("email"));
            acct.setPassword(AES.decrypt(rs.getString("password")));
            acct.setSite(rs.getString("site"));
            accts.add(acct);
        }
        return accts;
    }
    
}
