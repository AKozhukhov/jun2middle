package ru.itone.jun2middle.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itone.jun2middle.shop.facade.UserFacade;
import ru.itone.jun2middle.shop.model.entity.User;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public User create(@RequestBody User user) {
        return userFacade.create(user);
    }
}
