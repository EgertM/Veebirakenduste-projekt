
package com.veebirakendus.Attempt1.configuration;

import com.veebirakendus.Attempt1.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}