package com.akram.product.config;

import com.akram.product.model.Customer;
import com.akram.product.model.Product;
import com.akram.product.repo.CustomerRepo;
import com.akram.product.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Database {

//    private static final Logger log = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner init(CustomerRepo repository, ProductRepo productRepo) {

        return args -> {
            log.info("Preloading " + productRepo
                    .save(Product.builder().id(1).name("Product_1").price(10).build()));
            log.info("Preloading " + repository
                    .save(Customer.builder().id(1).name("Akram").build()));
        };
    }
}
