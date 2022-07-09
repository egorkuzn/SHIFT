package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.repos.model.CardEntity;

import java.util.List;

public interface CardsRepo extends JpaRepository<CardEntity, Long> {
    List<Long> findAllByOwnerIDAndCategory(Long id, String category);
    CardEntity findFirstByOwnerIDAndId(Long ownerID, Long cardId);
}
