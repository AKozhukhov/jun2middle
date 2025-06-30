package ru.itone.jun2middle.shop.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.mapper.UserMapper;
import ru.itone.jun2middle.shop.model.dto.user.CreatedUserDto;
import ru.itone.jun2middle.shop.model.dto.user.UserDto;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.service.UserService;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Добавление нового пользователя
     *
     * @param userDto Объект UserDto
     * @return CreatedUserDto
     */
    public CreatedUserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User created = userService.create(user);
        return userMapper.toCreatedDto(created);
    }
}
