package sample.cafekisok.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.cafekisok.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Component
public class ProductNumberFactory {
    private ProductRepository productRepository;

    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        return String.format("%03d", nextProductNumberInt);
    }
}
