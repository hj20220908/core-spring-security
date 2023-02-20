package security.coreSpringSecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import security.coreSpringSecurity.repository.ResourcesRepository;
import security.coreSpringSecurity.service.SecurityResourceService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository);
        return securityResourceService;
    }

}
