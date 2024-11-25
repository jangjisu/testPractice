package sample.cafekisok.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekisok.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekisok.spring.api.service.product.request.ProductCreateServiceRequest;
import sample.cafekisok.spring.api.service.product.response.ProductResponse;
import sample.cafekisok.spring.domain.product.Product;
import sample.cafekisok.spring.domain.product.ProductRepository;
import sample.cafekisok.spring.domain.product.ProductSellingStatus;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductNumberFactory productNumberFactory;

    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .toList();
    }
}
