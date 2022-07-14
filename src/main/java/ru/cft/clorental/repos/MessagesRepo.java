package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.repos.model.DiscussionEntity;

public interface MessagesRepo extends JpaRepository<DiscussionEntity, Long> {

}
