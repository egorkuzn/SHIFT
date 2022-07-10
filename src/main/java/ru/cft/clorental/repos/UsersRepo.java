package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.clorental.repos.model.UserEntity;

@Repository
public interface UsersRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByHashAndEmail(String hash, String email);
    UserEntity findFirstById(Long id);

    @Override
    <S extends UserEntity> S save(S s);
}
