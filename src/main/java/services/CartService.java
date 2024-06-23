package services;

import com.coffee.entities.Cart;
import com.coffee.entities.CartItem;
import com.coffee.entities.Coffee;
import com.coffee.entities.User;
import com.coffee.repositories.CartItemRepository;
import com.coffee.repositories.CartRepository;

import java.time.LocalDateTime;

public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

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

    public Cart updateCartItem(Long cartId, Long itemId, int quantity){
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        Cart cart = cartItem.getCart();
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    public Cart removeFromCart(Long cartId, Long itemId){
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found"));
        Cart cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }




}
