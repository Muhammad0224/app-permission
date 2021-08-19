package uz.pdp.apppermission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.apppermission.domain.User;


@Configuration
@EnableJpaAuditing
public class AuditingConfig {
    @Bean
    AuditorAware<User> auditorAware(){
        return new SpringSecurityAuditAwareImpl();
    }
}
