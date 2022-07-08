package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.domain.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
