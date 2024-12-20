package sample.cafekisok.spring.api.service.product;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekisok.spring.IntegrationTestSupport;
import sample.cafekisok.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekisok.spring.api.service.product.response.ProductResponse;
import sample.cafekisok.spring.domain.product.Product;
import sample.cafekisok.spring.domain.product.ProductRepository;
import sample.cafekisok.spring.domain.product.ProductSellingStatus;
import sample.cafekisok.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekisok.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekisok.spring.domain.product.ProductType.HANDMADE;

@Transactional
class ProductServiceTest extends IntegrationTestSupport {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeAll() {
        // before class
    }

    @BeforeEach
    void setUp() {
        // before method
    }

    @DisplayName("신규 상품을 등록한다. 상품번호는 사장 최근 상품의 상품번호에서 1. 증가한 값이다.")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("002", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .containsExactlyInAnyOrder(
                tuple("001", HANDMADE, SELLING, "아메리카노", 4000),
                tuple("002", HANDMADE, SELLING, "카푸치노", 5000)
            );
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면. 상품번호는 001 이다.")
    @Test
    void createProductWhenProductsIsEmpty() {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(HANDMADE)
            .sellingStatus(SELLING)
            .name("카푸치노")
            .price(5000)
            .build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains("001", HANDMADE, SELLING, "카푸치노", 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
            .extracting("productNumber", "type", "sellingStatus", "name", "price")
            .contains(
                tuple("001", HANDMADE, SELLING, "카푸치노", 5000)
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

}
