package services;

import com.coffee.entities.Cart;
import com.coffee.entities.Order;
import com.coffee.entities.User;
import com.coffee.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    public Order createOrder(User user){
        Cart cart = cartService.createCart(user);
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(calculateTotalAmount(cart));
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);

    }

    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    private BigDecimal calculateTotalAmount(Cart cart) {
        // Calculate the total amount based on the items in the cart
        return new BigDecimal(0);
    }

}
