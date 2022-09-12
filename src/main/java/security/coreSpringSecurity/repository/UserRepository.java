package security.coreSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.coreSpringSecurity.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long>{

}
