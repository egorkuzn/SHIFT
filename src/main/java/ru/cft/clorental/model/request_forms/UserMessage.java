package ru.cft.clorental.model.request_forms;

import ru.cft.clorental.repos.model.UserEntity;

public class UserMessage {
    public Long id;
    public String email;
    public String surname;
    public String name;
    public String userIconURL;
    public String phone;

    public UserMessage(UserEntity user) {
        this.id = user.id;
        this.email = user.email;
        this.surname = user.surname;
        this.name = user.name;
        this.phone = user.phone;
        this.userIconURL = user.personalIcon.imageURL;
    }
}
