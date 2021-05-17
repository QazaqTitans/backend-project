package kz.reserve.backend.service;

import kz.reserve.backend.domain.Role;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.SignupRequest;
import kz.reserve.backend.payload.response.AllUserResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.UserResponse;
import kz.reserve.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        try {
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            }

            String password = serviceUtils.generatePassword(6);

            // Create new user's account
            User user = new User(signupRequest.getEmail(),
                    passwordEncoder.encode(password));

            user.setName(signupRequest.getName());
            user.setSurname(signupRequest.getSurname());
            user.setPhoneNumber(signupRequest.getPhone());

            Set<Role> roles = new HashSet<>();
            roles.add(Role.client);

            user.setRoles(roles);
            userRepository.save(user);

            String message = String.format("Hello %s! \n" +
                    "Welcome to our site.\n" +
                    "Your login: %s\n" +
                    "Your password: %s\n", user.getName(), user.getEmail(), password);

            serviceUtils.sendMessageToUser(user, "Registration", message);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public ResponseEntity<?> userInfo() {
        try {
            User user = serviceUtils.getPrincipal();

            return ResponseEntity.ok(new UserResponse(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public ResponseEntity<?> deleteUser(Long id) {
        try {
            userRepository.deleteById(id);

            return ResponseEntity.ok(new MessageResponse("Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(new AllUserResponse(users));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
