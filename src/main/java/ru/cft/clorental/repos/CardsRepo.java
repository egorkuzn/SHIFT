package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.clorental.repos.model.CardEntity;

import java.util.List;

@Repository
public interface CardsRepo extends JpaRepository<CardEntity, Long> {
    List<Long> findAllByOwnerIDAndCategory(Long id, String category);
    CardEntity findFirstByOwnerIDAndId(Long ownerID, Long cardId);
    CardEntity findFirstById(Long id);

    @Override
    <S extends CardEntity> S save(S s);
}
