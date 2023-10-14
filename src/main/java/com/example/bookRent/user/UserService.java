package com.example.bookRent.user;


import com.example.bookRent.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public User create(String username, String password ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }


    public User getUser(String username) {
        Optional<User> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> siteUserOptional = userRepository.findByUsername(username);
        if (siteUserOptional.isPresent()) {
            User siteUser = siteUserOptional.get();
            return passwordEncoder.matches(password, siteUser.getPassword());
        }
        return false;
    }
}
