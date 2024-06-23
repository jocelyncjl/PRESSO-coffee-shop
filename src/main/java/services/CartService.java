package services;

import com.coffee.entities.Cart;
import com.coffee.entities.CartItem;
import com.coffee.entities.Coffee;
import com.coffee.entities.User;
import com.coffee.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CoffeeService coffeeService;

    @Autowired
    public CartService(CartRepository cartRepository, CoffeeService coffeeService) {
        this.cartRepository = cartRepository;
        this.coffeeService = coffeeService;
    }

    /**
     * Adds an item to the user's cart.
     *
     * @param cart the cart containing the item to be added
     * @return the updated cart
     */
    public Cart addToCart(Cart cart) {
        // Get the user and the coffee from the cart
        User user = cart.getUser();
        Coffee coffee = cart.getCartItem().getCoffee();
        Integer quantity = cart.getCartItem().getQuantity();

        // Check if the user already has a cart
        Cart existingCart = cartRepository.findByUser(user).orElse(new Cart(user));
        CartItem existingCartItem = existingCart.getCartItem();

        // If the cart already contains the same coffee, update the quantity
        if (existingCartItem != null && existingCartItem.getCoffee().equals(coffee)) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            // Otherwise, create a new cart item and add it to the cart
            CartItem newCartItem = new CartItem(coffee, quantity);
            existingCart.setCartItem(newCartItem);
        }

        // Save the updated cart
        return cartRepository.save(existingCart);
    }
}
