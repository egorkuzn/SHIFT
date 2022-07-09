package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.repos.model.TermEntity;
import ru.cft.clorental.repos.model.UserEntity;

public interface TermRepo extends JpaRepository<TermEntity, Long> {
    TermEntity findByEmailCode(String emailCode);
}
