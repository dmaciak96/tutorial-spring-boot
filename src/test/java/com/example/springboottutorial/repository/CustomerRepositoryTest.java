package com.example.springboottutorial.repository;

import com.example.springboottutorial.domain.Customer;
import com.example.springboottutorial.domain.OrderHeader;
import com.example.springboottutorial.domain.embeded.Address;
import com.example.springboottutorial.domain.enumtype.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveCustomerWithOrder() {
        var address = new Address();
        address.setAddress("Testowa 7/123");
        address.setCity("Test");
        address.setState("Testowy");
        address.setZipCode("66-666");

        var customer = new Customer();
        customer.setCustomerName("test_customer");
        customer.setAddress(address);
        customer.setPhone("666-666-666");
        customer.setEmail("test@test.com");

        var orderHeader = new OrderHeader();
        orderHeader.setOrderStatus(OrderStatus.NEW);
        orderHeader.setShippingAddress(address);
        orderHeader.setBillToAddress(address);

        customer.addOrderHeader(orderHeader);
        var result = customerRepository.save(customer);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getOrderHeaders().size()).isEqualTo(1);
        assertThat(result.getOrderHeaders().iterator().next().getId()).isNotNull();
    }
}