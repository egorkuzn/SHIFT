package ru.cft.yellowrubberduck.repos;

import org.springframework.data.repository.CrudRepository;
import ru.cft.yellowrubberduck.model.User;

import java.util.UUID;

public interface TemproraryUsersRepos extends CrudRepository<User, UUID> {
}
