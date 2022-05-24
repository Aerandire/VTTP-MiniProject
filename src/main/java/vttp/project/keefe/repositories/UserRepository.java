package vttp.project.keefe.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.project.keefe.model.User;

import static vttp.project.keefe.repositories.Queries.*;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public Boolean save(User user) {
        int count = template.update(SQL_INSERT_USER,user.getEmail(),user.getName(),user.getPassword());

        if(count != 1){
            return false;
        }

        return true;
    }

    public User findByEmail(String email){
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_EMAIL,email);

        if(!rs.next())
            return null;
        
        User usr = new User();
        usr.setEmail(email);
        usr.setName(rs.getString("name"));
        usr.setPassword(rs.getString("password"));
        usr.setId(rs.getLong("id"));
        return usr;    
    }
    
}
