package services;

import com.coffee.entities.Cart;
import com.coffee.entities.CartItem;
import com.coffee.entities.Coffee;
import com.coffee.entities.User;
import com.coffee.repositories.CartItemRepository;
import com.coffee.repositories.CartRepository;

import java.time.LocalDateTime;

/**
 * The `CartService` class provides methods for managing a user's shopping cart.
 */
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Creates a new `Cart` for the specified `User`.
     *
     * @param user   The user for whom the cart is being created.
     * @return       The created `Cart` entity.
     */
    public Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    /**
     * Adds a `Coffee` item to the specified `Cart`.
     *
     * @param cartId     The ID of the cart to which the coffee should be added.
     * @param coffee     The `Coffee` item to be added to the cart.
     * @param quantity   The quantity of the coffee to be added.
     * @return           The updated `Cart` entity.
     */
    public Cart addToCart(Long cartId, Coffee coffee,int quantity){
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCoffee(coffee);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    /**
     * Updates the quantity of a `CartItem` in the specified `Cart`.
     *
     * @param cartId     The ID of the cart containing the cart item to be updated.
     * @param itemId     The ID of the cart item to be updated.
     * @param quantity   The new quantity for the cart item.
     * @return           The updated `Cart` entity.
     */
    public Cart updateCartItem(Long cartId, Long itemId, int quantity){
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        Cart cart = cartItem.getCart();
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    /**
     * Removes a `CartItem` from the specified `Cart`.
     *
     * @param cartId     The ID of the cart from which the cart item should be removed.
     * @param itemId     The ID of the cart item to be removed.
     * @return           The updated `Cart` entity.
     */
    public Cart removeFromCart(Long cartId, Long itemId){
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

}
