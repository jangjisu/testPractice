package sample.cafekisok.spring.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekisok.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekisok.spring.api.service.order.response.OrderResponse;
import sample.cafekisok.spring.domain.order.Order;
import sample.cafekisok.spring.domain.order.OrderRepository;
import sample.cafekisok.spring.domain.product.Product;
import sample.cafekisok.spring.domain.product.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registerDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Order order = Order.create(products, registerDateTime);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.of(savedOrder);
    }
}
