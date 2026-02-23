package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void handleAddProductToCart(String email, long productId, long quantity) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            // Check if user has cart
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cart.setSum(0);
                cart = this.cartRepository.save(cart);
            }

            // Find product
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();

                // Check if product already in cart
                CartDetail cartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);

                if (cartDetail == null) {
                    cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(product);
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setQuantity(quantity);
                    cart.setSum(cart.getSum() + (int) quantity);
                } else {
                    cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                    cart.setSum(cart.getSum() + (int) quantity);
                }
                this.cartDetailRepository.save(cartDetail);
                this.cartRepository.save(cart); // Update cart sum
            }
        }
    }

    public Cart getCartByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user != null) {
            return this.cartRepository.findByUser(user);
        }
        return null;
    }

    public void updateCartSum(Cart cart) {
        this.cartRepository.save(cart);
    }
}
