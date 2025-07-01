package ru.itone.jun2middle.shop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.repository.OrderRepository;
import ru.itone.jun2middle.shop.repository.ProductRepository;
import ru.itone.jun2middle.shop.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order create(Order order) throws RuntimeException {
        if (order.getProduct_id()==null) {
            throw new RuntimeException("product_id не должен быть пустым");
        } else if (!productRepository.existsById(order.getProduct_id())) {
            throw new RuntimeException("Продукта с указанным product_id не существует");
        }
        if (order.getUser_id()==null) {
            throw new RuntimeException("user_id не должен быть пустым");
        } else if (!userRepository.existsById(order.getUser_id())) {
            throw new RuntimeException("Пользователь с указанным user_id не существует");
        }
        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Order order) {
        orderRepository.deleteById(order.getId());
    }
}
