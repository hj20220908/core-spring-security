package security.coreSpringSecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import security.coreSpringSecurity.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}
