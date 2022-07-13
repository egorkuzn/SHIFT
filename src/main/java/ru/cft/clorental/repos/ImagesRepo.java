package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.repos.model.ImageEntity;

public interface ImagesRepo extends JpaRepository<ImageEntity, Long> {
    ImageEntity findFirstById(Long id);
}
