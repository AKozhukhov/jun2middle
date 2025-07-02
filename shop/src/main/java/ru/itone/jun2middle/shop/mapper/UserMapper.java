package ru.itone.jun2middle.shop.mapper;

import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.dto.user.CreatedUserDto;
import ru.itone.jun2middle.shop.model.dto.user.UserDto;
import ru.itone.jun2middle.shop.model.entity.User;

/**
 * Класс для преобразования Entity и DTO User
 */
@Service
public class UserMapper {

    /**
     * Преобразование UserDto в User
     *
     * @param userDto Объект UserDto
     * @return User (Entity)
     */
    public User toEntity(UserDto userDto) {
        return new User(
                null,
                userDto.getFio(),
                userDto.getEmail(),
                userDto.getLocationX(),
                userDto.getLocationY());
    }

    /**
     * Преобразование User в CreatedUserDto
     *
     * @param user User (Entity)
     * @return Объект CreatedUserDto
     */
    public CreatedUserDto toCreatedDto(User user) {
        return CreatedUserDto.builder()
                .id(user.getId())
                .fio(user.getFio())
                .email(user.getEmail())
                .locationX(user.getLocationX())
                .locationY(user.getLocationY())
                .build();
    }
}
