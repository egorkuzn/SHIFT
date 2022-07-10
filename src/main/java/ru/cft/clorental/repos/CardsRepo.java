package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cft.clorental.repos.model.CardEntity;


@Repository
public interface CardsRepo extends JpaRepository<CardEntity, Long> {
    CardEntity findFirstByOwnerIDAndId(Long ownerID, Long cardID);
    CardEntity findFirstById(Long id);

    @Override
    <S extends CardEntity> S save(S s);
}
