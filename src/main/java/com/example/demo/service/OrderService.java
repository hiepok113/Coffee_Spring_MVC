package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, CartRepository cartRepository, CartDetailRepository cartDetailRepository, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.emailService = emailService;
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {

        // 1. Create Order
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null && !cartDetails.isEmpty()) {
                
                // Create new order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");
                
                double totalPrice = 0;
                for (CartDetail cd : cartDetails) {
                    totalPrice += cd.getPrice() * cd.getQuantity();
                }
                order.setTotalPrice(totalPrice);
                order = this.orderRepository.save(order);
                
                // 2. Create OrderDetails
                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                    
                    // Delete cart detail
                    this.cartDetailRepository.deleteById(cd.getId());
                }
                
                // 3. Clear cart
                cart.setSum(0);
                if (cart.getCartDetails() != null) {
                    cart.getCartDetails().clear();
                }
                this.cartRepository.save(cart);
                
                // 4. Update session (nếu có session từ MVC, API thì sẽ skip)
                if (session != null) {
                    session.setAttribute("sum", 0);
                }

                // 5. Gửi email báo cáo đơn hàng mới (Dùng giao diện HTML)
                String htmlTemplate = "<div style='font-family: Arial, sans-serif; padding: 20px; text-align: center; border: 1px solid #ddd; max-width: 500px; margin: auto; border-radius: 10px;'>" +
                                      "<h2 style='color: #4CAF50;'>🛍️ CHÚC MỪNG BẠN ĐÃ ĐẶT HÀNG THÀNH CÔNG!</h2>" +
                                      "<p>Xin chào <b>" + user.getFullName() + "</b>,</p>" +
                                      "<p>Đơn hàng <b>#" + order.getId() + "</b> của bạn đã được ghi nhận vào hệ thống.</p>" +
                                      "<h3 style='color: #d9534f;'>Tổng tiền: " + totalPrice + " VNĐ</h3>" +
                                      "<hr style='border-top: 1px dashed #ccc;'/>" +
                                      "<div style='text-align: left; padding: 10px;'>" +
                                      "<p>👤 <b>Người nhận:</b> " + receiverName + "</p>" +
                                      "<p>📞 <b>SĐT:</b> " + receiverPhone + "</p>" +
                                      "<p>🏠 <b>Địa chỉ:</b> " + receiverAddress + "</p>" +
                                      "</div>" +
                                      "<p style='color: #888; font-size: 12px;'>Chúng tôi sẽ sớm giao hàng đến bạn. Cảm ơn bạn đã tin tưởng Cafe Spring Boot!</p>" +
                                      "</div>";

                this.emailService.sendHtmlEmail(
                    user.getEmail(),
                    "🎉 Xác nhận đơn hàng thành công #" + order.getId(),
                    htmlTemplate
                );
            }
        }
    }

    public List<Order> fetchOrderByUser(User user) {
        return this.orderRepository.findByUser(user);
    }

    public List<Order> fetchAllOrders() {
        return this.orderRepository.findAll();
    }

    public java.util.Optional<Order> fetchOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public void deleteOrderById(long id) {
        // Delete order details first if any. Wait, mappedBy has CascadeType? If not, do it manually.
        // Actually best to find details and delete them.
        java.util.Optional<Order> orderOpt = this.orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            List<OrderDetail> details = order.getOrderDetails();
            if (details != null) {
                for (OrderDetail cd : details) {
                    this.orderDetailRepository.deleteById(cd.getId());
                }
            }
            this.orderRepository.deleteById(id);
        }
    }

    public void updateOrder(Order order) {
        java.util.Optional<Order> currentOrderOpt = this.orderRepository.findById(order.getId());
        if (currentOrderOpt.isPresent()) {
            Order currentOrder = currentOrderOpt.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);

            // Gửi email thông báo khi Admin đổi Status đơn (Dùng HTML)
            if (currentOrder.getUser() != null) {
                String htmlTemplate = "<div style='font-family: Arial, sans-serif; padding: 20px; text-align: center; border: 1px solid #ddd; max-width: 500px; margin: auto; border-radius: 10px;'>" +
                                      "<h2 style='color: #2196F3;'>CẬP NHẬT TRẠNG THÁI GIAO HÀNG 🚚</h2>" +
                                      "<p>Xin chào <b>" + currentOrder.getUser().getFullName() + "</b>,</p>" +
                                      "<p>Đơn hàng <b>#" + currentOrder.getId() + "</b> của bạn vừa được Shop cập nhật sang trạng thái mới:</p>" +
                                      "<h3 style='background-color: #f1f1f1; padding: 10px; display: inline-block; border-radius: 5px; color: #ff9800;'>[ " + order.getStatus() + " ]</h3>" +
                                      "<p style='color: #888; font-size: 13px;'>Bạn có thể vào lịch sử mua hàng trên App để kiểm tra chi tiết.</p>" +
                                      "</div>";

                this.emailService.sendHtmlEmail(
                    currentOrder.getUser().getEmail(),
                    "📦 Cập nhật trạng thái đơn hàng #" + currentOrder.getId(),
                    htmlTemplate
                );
            }
        }
    }
}
