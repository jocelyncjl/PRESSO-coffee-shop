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

/**
 * The `OrderService` class provides methods for managing orders for coffee products.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    /**
     * Creates a new `Order` for the specified `User`.
     *
     * @param user  The user for whom the order is being created.
     * @return      The created `Order` entity.
     */
    public Order createOrder(User user){
        Cart cart = cartService.createCart(user);
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(calculateTotalAmount(cart));
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);

    }

    /**
     * Retrieves the order history for the specified `User`.
     *
     * @param userId  The ID of the user for whom to retrieve the order history.
     * @return        A list of `Order` entities for the specified user.
     */
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    /**
     * Calculates the total amount for the specified `Cart`.
     *
     * @param cart  The `Cart` for which to calculate the total amount.
     * @return      The total amount as a `BigDecimal`.
     */
    private BigDecimal calculateTotalAmount(Cart cart) {
        // Calculate the total amount based on the items in the cart
        return new BigDecimal(0);
    }

}
