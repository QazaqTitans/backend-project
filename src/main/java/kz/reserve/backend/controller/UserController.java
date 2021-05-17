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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("hasAuthority('superAdmin')")
@RequestMapping("/api/user")
public class UserController {
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

    @GetMapping()
    public ResponseEntity<?> getCategories() {
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@Valid @Min(1) @PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
