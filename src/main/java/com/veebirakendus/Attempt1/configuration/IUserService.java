package com.veebirakendus.Attempt1.configuration;

import com.veebirakendus.Attempt1.entity.User;
import com.veebirakendus.Attempt1.entity.UserDto;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}