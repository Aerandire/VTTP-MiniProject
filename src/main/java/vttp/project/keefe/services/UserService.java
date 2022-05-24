package vttp.project.keefe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.project.keefe.model.User;
import vttp.project.keefe.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository uRepo;

    public User loadUserByEmail(String email) {
        User user  = uRepo.findByEmail(email);
        return user;
    }

    public boolean saveUser(User usr){       
        Boolean saved = uRepo.save(usr);

        if(saved != true)
            return false;
        else
            return true;
    }
    
}
