package sample.cafekisok.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekisok.spring.domain.product.Product;
import sample.cafekisok.spring.domain.product.ProductRepository;
import sample.cafekisok.spring.domain.product.ProductSellingStatus;
import sample.cafekisok.spring.domain.product.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekisok.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;
import static sample.cafekisok.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekisok.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 시간 범위의 특정 주문 상태를 가진 주문들을 조회한다.")
    @Test
    void findOrdersByTimeAndOrderStatus() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, SELLING, "카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, SELLING, "팥빙수", 7000);

        productRepository.saveAll(List.of(product1, product2, product3));

        Order order1 = createOrder(PAYMENT_COMPLETED,
            LocalDateTime.of(2024, 10, 31, 0, 0),
            List.of(product1, product2, product3));

        Order order2 = createOrder(PAYMENT_COMPLETED,
            LocalDateTime.of(2024, 10, 31, 23, 59),
            List.of(product1, product2, product2));

        Order order3 = createOrder(PAYMENT_COMPLETED,
            LocalDateTime.of(2024, 11, 1, 0, 0),
            List.of(product1, product1, product2));

        orderRepository.saveAll(List.of(order1, order2, order3));

        // when
        List<Order> orders = orderRepository.findOrdersBy(LocalDateTime.of(2024, 10, 31, 0, 0),
            LocalDateTime.of(2024, 11, 1, 0, 0),
            PAYMENT_COMPLETED);

        // then
        assertThat(orders).hasSize(2)
            .extracting("orderStatus", "registeredDateTime", "totalPrice")
            .containsExactlyInAnyOrder(
                tuple(PAYMENT_COMPLETED, LocalDateTime.of(2024, 10, 31, 0, 0), 15500),
                tuple(PAYMENT_COMPLETED, LocalDateTime.of(2024, 10, 31, 23, 59), 13000)
            );
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus status, String name, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingStatus(status)
            .name(name)
            .price(price)
            .build();
    }

    private Order createOrder(OrderStatus orderStatus, LocalDateTime registeredDateTime, List<Product> products) {
        return Order.builder()
            .registeredDateTime(registeredDateTime)
            .orderStatus(orderStatus)
            .products(products)
            .build();
    }

}
