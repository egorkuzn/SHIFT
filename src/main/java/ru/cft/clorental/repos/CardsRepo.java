package ru.cft.clorental.repos;

import org.springframework.data.repository.CrudRepository;
import ru.cft.clorental.domain.CardEntity;

import java.util.List;

public interface CardsRepo extends CrudRepository<CardEntity, Long> {
    List<Long> findAllByOAndOwnerIDAndAndCategory(Long id, String category);
}
