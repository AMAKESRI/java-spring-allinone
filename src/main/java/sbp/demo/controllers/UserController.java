package sbp.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("principal")
public class UserController {

    @GetMapping
    Principal getCurrentUser(Principal principal) {
        return principal;
    }
}
