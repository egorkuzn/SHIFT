package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.model.UserEntity;

public interface UsersRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
