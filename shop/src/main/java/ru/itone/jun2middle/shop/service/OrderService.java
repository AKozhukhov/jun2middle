package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.model.entity.enums.Status;
import ru.itone.jun2middle.shop.repository.OrderRepository;
import ru.itone.jun2middle.shop.repository.ProductRepository;
import ru.itone.jun2middle.shop.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /**
     * Создает новый заказ
     *
     * @param order Order (Entity)
     * @return созданный Order со статусом NEW
     */
    public Order create(Order order) {
        if (order.getProductId()==null) {
            throw new RuntimeException("product_id не должен быть пустым");
        } else if (!productRepository.existsById(order.getProductId())) {
            throw new RuntimeException("Продукта с указанным product_id не существует");
        }
        if (order.getUserId()==null) {
            throw new RuntimeException("user_id не должен быть пустым");
        } else if (!userRepository.existsById(order.getUserId())) {
            throw new RuntimeException("Пользователь с указанным user_id не существует");
        }
        return orderRepository.save(order);
    }

    /**
     * Изменяет статус заказа
     *
     * @param order  исходный Order
     * @param status статус заказа
     * @return изменённый Order
     */
    public Order changeStatus(Order order, Status status) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if(optionalOrder.isEmpty()) {
            throw new RuntimeException("заказ с таким id не найден");
        }
        Order foundOrder = optionalOrder.get();
        foundOrder.setStatus(status);
        foundOrder.setDescription("");
        return orderRepository.save(foundOrder);
    }

    /**
     * Устанавливает у заказа статус "ERROR" и описание ошибки
     *
     * @param order исходный Order
     * @param error Текст ошибки
     * @return изменённый Order
     */
    public Order setError(Order order, String error) {
        Optional<Order> optionalOrder = orderRepository.findById(order.getId());
        if(optionalOrder.isEmpty()) {
            throw new RuntimeException("заказ с таким id не найден");
        }
        Order foundOrder = optionalOrder.get();
        foundOrder.setStatus(Status.ERROR);
        foundOrder.setDescription(error);
        return orderRepository.save(foundOrder);
    }
}
