package com.akram.product.config;

import com.akram.product.model.Customer;
import com.akram.product.model.Order;
import com.akram.product.model.OrderItem;
import com.akram.product.model.Product;
import com.akram.product.repo.CustomerRepo;
import com.akram.product.repo.OrderRepo;
import com.akram.product.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Configuration
@Slf4j
public class Database {

//    private static final Logger log = LoggerFactory.getLogger(Database.class);

    @Bean
    @Transactional
    CommandLineRunner init(CustomerRepo customerRepo, ProductRepo productRepo, OrderRepo orderRepo) {

        return args -> {
            Product product = Product.builder().name("Product_1").price(10L).balance(100).build();
            log.info("Preloading " + productRepo.save(product));
            Customer customer = Customer.builder().name("Akram").creditLimit(100L).currentCredit(0L).build();
            log.info("Preloading " + customerRepo.save(customer));
            OrderItem orderItem = OrderItem.builder().referredProduct(product).count(2).build();
            Order order = Order.builder().id(1).referredCustomer(customer).totalPrice(20L)
                    .currentOrderItems(new ArrayList<>()).build();
            order.addOrderItem(orderItem);
//            log.info("Preloading " + orderRepo.save(order));
        };
    }
}
