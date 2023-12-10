package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Product;
import com.example.springboottutorial.domain.enumtype.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveShouldGenerateProperUuid() {
        var result = productRepository.save(new Product("test description", ProductStatus.NEW));
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getUpdatedDate()).isNotNull();
        assertThat(result.getDescription()).isEqualTo("test description");
        assertThat(result.getProductStatus()).isEqualTo(ProductStatus.NEW);
    }
}
