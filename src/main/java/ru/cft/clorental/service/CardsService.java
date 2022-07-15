package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.CardsRepo;
import ru.cft.clorental.repos.UsersRepo;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.repos.model.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CardsService {
    final CardsRepo cardsRepo;
    final UsersRepo usersRepo;
    HashMap<String, String> translator = new HashMap<>();

    @Autowired
    CardsService(CardsRepo cardsRepo, UsersRepo usersRepo){
        this.cardsRepo = cardsRepo;
        this.usersRepo = usersRepo;
        translatorInitializer();
    }

    private void translatorInitializer() {
        translator.put("appliances", "Бытовая техника");
        translator.put("recreation", "Отдых");
        translator.put("home", "Всё для дома");
        translator.put("clothes", "Одежда");
        translator.put("sport", "Спорт");
        translator.put("hobby", "Отдых и хобби");
        translator.put("things", "Личные вещи");
        translator.put("xxx", "18+");
        translator.put("dacha", "Для дома и дачи");
    }

    public CardMessage findById(Long id){
        CardEntity card = cardsRepo.findFirstById(id);
        UserEntity owner = usersRepo.findFirstByIdAndVerified(card.ownerID, true);

        UserEntity customer = null;

        if(card.customerId != null)
            customer = usersRepo.findFirstByIdAndVerified(card.customerId, true);

        return new CardMessage(card, owner, customer);
    }

    public List<Long> findByCategory(String category) {
        List<Long> list = new ArrayList<>();
        cardsRepo.findAllByCategoryAndRent(translator.get(category), false).forEach(
                cardEntity -> list.add(cardEntity.id)
        );

        return list;
    }
}
