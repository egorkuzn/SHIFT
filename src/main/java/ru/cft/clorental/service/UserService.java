package ru.cft.clorental.service;

import ru.cft.clorental.domain.RoleEntity;
import ru.cft.clorental.domain.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    RoleEntity saveRole(RoleEntity role);
    void addRoleToUser(String username, String roleName);
    UserEntity getUser(String username);
    List<UserEntity> getUsers();
}
