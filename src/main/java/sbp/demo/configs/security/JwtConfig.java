package sbp.demo.configs.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import sbp.demo.factories.yaml.YamlPropertySourceFactory;

@Getter
@Configuration
@PropertySource(value = "classpath:application-security.yml", factory = YamlPropertySourceFactory.class)
public class JwtConfig {

    @Value("${security.jwt.token-validity}")
    private long tokenValidity;

    @Value("${security.jwt.secret}")
    private String secret;
}
