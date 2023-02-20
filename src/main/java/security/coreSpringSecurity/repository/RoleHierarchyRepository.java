package security.coreSpringSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.coreSpringSecurity.domain.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}