package security.coreSpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import security.coreSpringSecurity.repository.AccessIpRepository;
import security.coreSpringSecurity.repository.ResourcesRepository;
import security.coreSpringSecurity.service.SecurityResourceService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        SecurityResourceService SecurityResourceService = new SecurityResourceService(resourcesRepository, accessIpRepository);
        return SecurityResourceService;
    }

}