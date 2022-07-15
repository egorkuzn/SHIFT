package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.clorental.repos.model.UserEntity;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findFirstByHashAndEmailAndVerified(String hash, String email, boolean isVerified);
    UserEntity findFirstByIdAndVerified(Long id, boolean isVerified);
    UserEntity findFirstById(Long id);
    UserEntity findFirstByIdAndEmailCode(Long id, String emailCode);
    UserEntity findFirstByIdAndEmailAndHash(Long id, String email, String hash);
    List<UserEntity> findAllByEmailAndVerified(String email, boolean isVerified);

    @Override
    <S extends UserEntity> S save(S s);
}
