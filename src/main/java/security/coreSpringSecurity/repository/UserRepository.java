package security.coreSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.coreSpringSecurity.domain.entity.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
  int countByUsername(String username);
}