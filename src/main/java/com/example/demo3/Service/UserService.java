package com.example.demo3.Service;

import com.example.demo3.Model.User;
import com.example.demo3.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}
