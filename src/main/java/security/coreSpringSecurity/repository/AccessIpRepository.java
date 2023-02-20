package security.coreSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.coreSpringSecurity.domain.entity.AccessIp;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String IpAddress);

}