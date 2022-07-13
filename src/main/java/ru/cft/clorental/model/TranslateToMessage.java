package ru.cft.clorental.model;

import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.UserEntity;

public class TranslateToMessage {
    public static CardMessage oneCard(CardEntity firstById, UserEntity owner, UserEntity customer) {
        return new CardMessage(firstById, owner, customer);
    }
}
