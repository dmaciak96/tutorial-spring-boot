package com.example.springboottutorial.bootstrap;

import com.example.springboottutorial.domain.Customer;
import com.example.springboottutorial.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Bootstrap implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    private final BootstrapService bootstrapService;
    private final CustomerRepository customerRepository;

    public Bootstrap(BootstrapService bootstrapService, CustomerRepository customerRepository) {
        this.bootstrapService = bootstrapService;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        optimisticLockingError();
    }

    private void optimisticLockingError() {
        var customer = new Customer();
        customer.setCustomerName("Testing Version");
        customer.setVersion(0);
        var savedCustomer = customerRepository.save(customer);

        savedCustomer.setCustomerName("Testing Version 2");
        customerRepository.save(savedCustomer);

        savedCustomer.setCustomerName("Testing version 3");
        customerRepository.save(savedCustomer);
    }

    private void optimisticLockingErrorFix() {
        var customer = new Customer();
        customer.setCustomerName("Testing Version");
        customer.setVersion(0);
        var savedCustomer = customerRepository.save(customer);

        savedCustomer.setCustomerName("Testing Version 2");
        savedCustomer = customerRepository.save(savedCustomer);

        savedCustomer.setCustomerName("Testing version 3");
        customerRepository.save(savedCustomer);
    }
}
