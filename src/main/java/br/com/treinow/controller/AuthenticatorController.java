package br.com.treinow.controller;

import br.com.treinow.dtos.LoginDto;
import br.com.treinow.models.entities.UserEntity;
import br.com.treinow.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Logout successfully");
    }

}
