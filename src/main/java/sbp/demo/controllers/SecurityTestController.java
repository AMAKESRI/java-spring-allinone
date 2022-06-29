package sbp.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security-test")
public class SecurityTestController {

    @GetMapping("/public")
    String publicURL() {
        return "This endpoint is public";
    }

    @GetMapping("/private")
    String privateURL() {
        return "This endpoint is private";
    }

    @GetMapping("/user")
    String userURL() {
        return "This endpoint is connected user with role USER";
    }

    @GetMapping("/admin")
    String adminURL() {
        return "This endpoint is connected user with role ADMIN";
    }
}
