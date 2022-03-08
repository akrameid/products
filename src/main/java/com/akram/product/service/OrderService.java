package com.akram.product.service;

import com.akram.product.dto.CustomerOrder;
import com.akram.product.dto.createorder.OrderItem;
import com.akram.product.dto.createorder.Request;
import com.akram.product.dto.createorder.Response;
import com.akram.product.model.Order;
import com.akram.product.model.Product;
import com.akram.product.repo.CustomerRepo;
import com.akram.product.repo.OrderRepo;
import com.akram.product.repo.ProductRepo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;


    public List<CustomerOrder> getAll() {
        List<CustomerOrder> orders = new ArrayList<>();
        List<Order>allOrders = orderRepo.findAll();
        for (Order currentOrder : allOrders) {
            orders.add(CustomerOrder.builder()
                            .customerName(currentOrder.getCustomer().getName())
                    .build());
        }

        return orders;
    }

    public Response createOrder(Request request) {
        List<com.akram.product.model.OrderItem> dbOrderItems = new ArrayList<>();
        for (OrderItem orderDetail : request.getOrderDetails()) {
            Optional<Product> product = productRepo.findById(orderDetail.getProductId());
            product.ifPresent(value -> dbOrderItems.add(com.akram.product.model.OrderItem.builder()
                    .count(orderDetail.getCount())
                    .product(value)
                    .build()));
        }

        Order order = Order.builder()
                .customer(customerRepo.findByName(request.getCustomerName()))
                .orderItems(dbOrderItems)
                .build();
        orderRepo.save(order);
        return new Response("Added");
    }
}
