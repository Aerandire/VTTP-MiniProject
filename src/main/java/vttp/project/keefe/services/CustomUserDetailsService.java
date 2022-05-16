package vttp.project.keefe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vttp.project.keefe.model.CustomUserDetails;
import vttp.project.keefe.model.User;
import vttp.project.keefe.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository uRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = uRepo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        return new CustomUserDetails(user);
    }
    
}
