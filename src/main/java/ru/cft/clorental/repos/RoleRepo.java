package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.domain.RoleEntity;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
