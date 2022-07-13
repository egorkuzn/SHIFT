package ru.cft.clorental.model.request_forms;

import ru.cft.clorental.repos.model.UserEntity;

public class UserMessage {
    private final Long id;
    private final String email;
    private final String surname;
    private final String name;
    private final String userIonURL;
    private final String phone;

    public UserMessage(UserEntity user) {
        this.id = user.id;
        this.email = user.email;
        this.surname = user.surname;
        this.name = user.name;
        this.phone = user.phone;
        this.userIonURL = user.personalIcon.imageURL;
    }
}
