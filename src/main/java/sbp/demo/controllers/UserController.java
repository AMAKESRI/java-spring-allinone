package sbp.demo.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("me")
public class UserController {

    @GetMapping
    Principal getCurrentUser(UsernamePasswordAuthenticationToken principal) {
        principal.eraseCredentials(); // remove password during serialization
        return principal;
    }
}
