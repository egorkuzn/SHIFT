package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.clorental.repos.model.UserEntity;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByHashAndEmail(String hash, String email);
    UserEntity findFirstById(Long id);
    List<UserEntity> findAllByEmail(String email);

    @Override
    <S extends UserEntity> S save(S s);
}
