package kz.reserve.backend.controller;

import kz.reserve.backend.configuration.UserDetailsImpl;
import kz.reserve.backend.domain.Role;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.LoginRequest;
import kz.reserve.backend.payload.request.SignupRequest;
import kz.reserve.backend.payload.response.JwtResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.UserRepository;
import kz.reserve.backend.security.jwt.JwtUtils;
import kz.reserve.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @ModelAttribute LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getEmail()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @ModelAttribute SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

    @PostMapping("/me")
    public ResponseEntity<?> userInfo() {
        return userService.userInfo();
    }

    @PostMapping("/add-super-admin")
    public ResponseEntity<?> addSuperAdmin() {
        if (userRepository.existsByEmail("admin@test.io")) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: You can't create"));
        }

        // Create new user's account
        User user = new User("admin@test.io", passwordEncoder.encode("1234"));

        Set<Role> roles = new HashSet<>();
        roles.add(Role.superAdmin);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
