package com.example.buysell.services;

import com.example.buysell.enums.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false; // Егер қолданушы бұрыннан бар болса, жалған мәнді қайтару
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Қолданушыны дерекқорға сақтау
        userRepository.save(user);
        return true;
    }

}
