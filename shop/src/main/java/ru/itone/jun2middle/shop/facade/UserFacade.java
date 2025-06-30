package ru.itone.jun2middle.shop.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public User create(User user) {
        return userService.create(user);
    }
}
