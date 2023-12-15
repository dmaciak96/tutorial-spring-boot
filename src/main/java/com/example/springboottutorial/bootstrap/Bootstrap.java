package com.example.springboottutorial.bootstrap;

import com.example.springboottutorial.repository.OrderHeaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Component
public class Bootstrap implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    private OrderHeaderRepository orderHeaderRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        lazyInitializeError();
    }

    public void lazyInitializeError() {
        var uuid = UUID.fromString("fddf37fc-a058-4cb7-a028-4d506d71885a");
        var orderHeader = orderHeaderRepository.findById(uuid).orElseThrow();

        //Order lines has eager fetch
        orderHeader.getOrderLines().forEach(orderLine -> {
            log.info("OrderLine: {}", orderLine.getProduct().getDescription());

            //lazy initialization error (categories has lazy fetch) if run is not @Transactional
            orderLine.getProduct().getCategories().forEach(category ->
                    log.info("Category: {}", category.getDescription()));
        });
    }
}
