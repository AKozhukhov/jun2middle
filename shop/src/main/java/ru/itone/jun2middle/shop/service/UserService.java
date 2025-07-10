package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.exception.UserNotFoundException;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user){
        Optional<User> optionalUser = userRepository.findByFio(user.getFio());
        if (optionalUser.isEmpty()) {
            return userRepository.save(user);
        } else {
            User existingUser = optionalUser.get();
            existingUser.setEmail(user.getEmail());
            existingUser.setLocationX(user.getLocationX());
            existingUser.setLocationY(user.getLocationY());
            return userRepository.save(existingUser);
        }
    }

    public User getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Пользователь с указанным ID не найден");
        }
        return optionalUser.get();
    }
}
