package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.OrderHeader;
import com.example.springboottutorial.domain.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Test
    void saveShouldGenerateProperUuid() {
        var result = orderHeaderRepository.save(new OrderHeader("Test"));
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCustomerName()).isEqualTo("Test");
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getUpdatedDate()).isNotNull();
    }

    @Test
    void testSaveOrderWithLine() {
        var orderHeader = new OrderHeader("Test");
        var orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderHeader.addOrderLine(orderLine);

        var result = orderHeaderRepository.save(orderHeader);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCustomerName()).isEqualTo("Test");
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getUpdatedDate()).isNotNull();
        assertThat(result.getOrderLines()).hasSize(1);

        var resultOrderLine = result.getOrderLines().iterator().next();
        assertThat(resultOrderLine.getId()).isNotNull();
        assertThat(resultOrderLine.getCreatedDate()).isNotNull();
        assertThat(resultOrderLine.getUpdatedDate()).isNotNull();
        assertThat(resultOrderLine.getQuantityOrdered()).isEqualTo(5);
    }

    @Test
    void testEqualsMethod() {
        var oh1 = new OrderHeader();
        var oh2 = new OrderHeader();

        var uuid = UUID.randomUUID();
        oh1.setId(uuid);
        oh2.setId(uuid);
        assertThat(oh1).isEqualTo(oh2);
        oh1.setId(UUID.randomUUID());
        assertThat(oh1).isNotEqualTo(oh2);
    }
}