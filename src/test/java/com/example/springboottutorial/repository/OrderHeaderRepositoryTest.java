package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Customer;
import com.example.springboottutorial.domain.OrderHeader;
import com.example.springboottutorial.domain.OrderLine;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveShouldGenerateProperUuid() {
        var result = orderHeaderRepository.save(new OrderHeader());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreatedDate()).isNotNull();
        assertThat(result.getUpdatedDate()).isNotNull();
    }

    @Test
    void testSaveOrderWithLine() {
        var orderHeader = new OrderHeader();
        var orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderHeader.addOrderLine(orderLine);

        var result = orderHeaderRepository.save(orderHeader);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
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
    void testCascadeDelete() {
        var orderHeader = new OrderHeader();
        var customer = new Customer();
        customer.setCustomerName("test");
        orderHeader.setCustomer(customerRepository.save(customer));


        var orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderHeader.addOrderLine(orderLine);

        var saved = orderHeaderRepository.saveAndFlush(orderHeader);
        orderHeaderRepository.deleteById(saved.getId());
        orderHeaderRepository.flush();

        var result = orderHeaderRepository.findById(saved.getId());
        assertThat(result.isEmpty()).isTrue();
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