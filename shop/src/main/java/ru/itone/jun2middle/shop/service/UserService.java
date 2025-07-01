package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.User;
import ru.itone.jun2middle.shop.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

    public User getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        }
        return optionalUser.get();
    }
}
