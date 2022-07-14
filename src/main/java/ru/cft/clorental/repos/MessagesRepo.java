package ru.cft.clorental.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.clorental.repos.model.MessageEntity;

public interface MessagesRepo extends JpaRepository<MessageEntity, Long> {

}
