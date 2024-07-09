package com.pafassigment.fitplus.resource;

import com.pafassigment.fitplus.enumeration.AuthProvider;
import com.pafassigment.fitplus.exception.BadRequestException;
import com.pafassigment.fitplus.dao.UserDao;
import com.pafassigment.fitplus.response_dtos.ApiResponse;
import com.pafassigment.fitplus.response_dtos.AuthResponse;
import com.pafassigment.fitplus.response_dtos.LoginRequest;
import com.pafassigment.fitplus.response_dtos.SignUpRequest;
import com.pafassigment.fitplus.security.TokenProvider;
import com.pafassigment.fitplus.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthResources {


    private final AuthenticationManager authenticationManager;

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    public AuthResources(AuthenticationManager authenticationManager, UserDao userDao, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = this.tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(this.userDao.existsByEmail(signUpRequest.getEmail()))) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        User result = this.userDao.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
