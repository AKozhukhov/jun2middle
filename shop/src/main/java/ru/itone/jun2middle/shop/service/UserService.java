package ru.itone.jun2middle.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Добавление нового пользователя в БД, либо обновление записи, если пользователь с таким ФИО существует
     *
     * @param user User (Entity)
     * @return created User (Entity)
     */
    @Transactional
    public User create(User user){
        User existingUser = userRepository.findByFio(user.getFio());
        if (existingUser==null) {
            return userRepository.save(user);
        } else {
            existingUser.setEmail(user.getEmail());
            existingUser.setLocation_x(user.getLocation_x());
            existingUser.setLocation_y(user.getLocation_y());
            return userRepository.save(existingUser);
        }
    }
}
