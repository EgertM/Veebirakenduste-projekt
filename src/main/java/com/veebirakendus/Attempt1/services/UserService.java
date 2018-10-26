/*
package com.veebirakendus.Attempt1.services;


import com.veebirakendus.Attempt1.entity.User;
import com.veebirakendus.Attempt1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Bean
    public User updateUserStatus(Principal principal) {
        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        String uid = (String)details.get("sub");
        String firstName = (String)details.get("given_name");
        String lastName = (String)details.get("family_name");
        //String name = (String)details.get("name");
        String email = (String)details.get("email");

        User user = userRepository.findByEmail(email);

        if(user == null) {
            user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGoogleUid(uid);
            userRepository.save(user);
        }

        return user;
    }

    public User getUser(Principal principal) {
        if(principal == null) {
            return null;
        }

        Map<String, Object> details = (Map<String, Object>) ((OAuth2Authentication) principal).getUserAuthentication().getDetails();

        User User = userRepository.findByEmail((String)details.get("email"));

        if(User == null) {
            return updateUserStatus(principal);
        }

        return User;
    }
}*/
