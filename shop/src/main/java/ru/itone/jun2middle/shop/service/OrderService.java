package ru.itone.jun2middle.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itone.jun2middle.shop.exception.OrderCreationException;
import ru.itone.jun2middle.shop.exception.OrderNotFoundException;
import ru.itone.jun2middle.shop.exception.ProductNotFoundException;
import ru.itone.jun2middle.shop.exception.UserNotFoundException;
import ru.itone.jun2middle.shop.model.entity.Order;
import ru.itone.jun2middle.shop.model.entity.Product;
import ru.itone.jun2middle.shop.model.entity.User;
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
            throw new OrderCreationException("product_id не должен быть пустым");
        } else if (!productRepository.existsById(order.getProductId())) {
            throw new ProductNotFoundException("Продукта с указанным product_id не существует");
        }
        if (order.getUserId()==null) {
            throw new OrderCreationException("user_id не должен быть пустым");
        } else if (!userRepository.existsById(order.getUserId())) {
            throw new UserNotFoundException("Пользователь с указанным user_id не существует");
        }

        Optional<Product> optionalProduct = productRepository.findById(order.getProductId());
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getPrice()==null) {
                throw new OrderCreationException("Невозможно создать заказ с неоценённым товаром");
            }
        }

        Optional<User> optionalUser = userRepository.findById(order.getUserId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if((user.getLocationX()<0) || (user.getLocationX()>10) ||
                    (user.getLocationY()<0) || (user.getLocationY()>10)) {
                throw new OrderCreationException("недопустимое местоположение пользователя");
            }
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
            throw new OrderNotFoundException("заказ с указанным ID не найден");
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
            throw new OrderNotFoundException("заказ с указанным ID не найден");
        }
        Order foundOrder = optionalOrder.get();
        foundOrder.setStatus(Status.ERROR);
        foundOrder.setDescription(error);
        return orderRepository.save(foundOrder);
    }
}
