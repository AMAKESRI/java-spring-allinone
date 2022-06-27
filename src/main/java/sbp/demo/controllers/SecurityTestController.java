package sbp.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security-test")
public class SecurityTestController {

    @GetMapping
    String testSecurity() {
        return "Spring Security is well configured";
    }
}
