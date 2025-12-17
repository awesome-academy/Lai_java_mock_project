package com.example.booking_tour.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.booking_tour.entity.User;
import com.example.booking_tour.entity.User.Provider;
import com.example.booking_tour.repository.UserRepository;

@Component
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setPhone("0387588688");
            user.setRole(User.Role.ADMIN);
            user.setProvider(Provider.LOCAL);
            userRepository.save(user);

            System.out.println("Seeder: Inserted default users");
        }
    }

}
