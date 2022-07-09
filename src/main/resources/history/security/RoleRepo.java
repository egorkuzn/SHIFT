package history.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.model.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
