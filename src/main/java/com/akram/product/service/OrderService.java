package com.akram.product.service;

import com.akram.product.dto.CustomerOrderDto;
import com.akram.product.dto.order.OrderItemDto;
import com.akram.product.dto.order.CreateOrderRequestDto;
import com.akram.product.exception.CustomerCreditNotEnoughException;
import com.akram.product.exception.ProductBalanceNotEnoughException;
import com.akram.product.exception.ProductIdNotFoundException;
import com.akram.product.model.Customer;
import com.akram.product.model.Order;
import com.akram.product.model.OrderItem;
import com.akram.product.model.Product;
import com.akram.product.repo.CustomerRepo;
import com.akram.product.repo.OrderItemRepo;
import com.akram.product.repo.OrderRepo;
import com.akram.product.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;


    public List<CustomerOrderDto> getAll() {
        List<CustomerOrderDto> orders = new ArrayList<>();
        List<Order> allOrders = orderRepo.findAll();
        for (Order currentOrder : allOrders) {
            List<OrderItemDto> orderItemDtos = new ArrayList<>();
            for (OrderItem orderItem : currentOrder.getCurrentOrderItems()) {
                orderItemDtos.add(OrderItemDto.builder()
                        .productId(orderItem.getReferredProduct().getId())
                        .productName(orderItem.getReferredProduct().getName())
                        .productPrice(orderItem.getReferredProduct().getPrice())
                        .count(orderItem.getCount())
                        .build());
            }
            orders.add(CustomerOrderDto.builder()
                    .customerName(currentOrder.getReferredCustomer().getName())
                    .orderDetails(orderItemDtos)
                    .totalPrice(currentOrder.getTotalPrice())
                    .id(currentOrder.getId())
                    .build());
        }

        return orders;
    }

    public String createOrder(CreateOrderRequestDto createOrderRequestDto) {
        List<OrderItem> dbOrderItems = new ArrayList<>();
        long totalPrice = 0;
        Map<Long, Integer> usedProductsCounts = new HashMap<>();
        for (OrderItemDto orderDetail : createOrderRequestDto.getOrderDetails()) {
            Product product = productService.getByName(orderDetail.getProductName());
            if (!validateProductBalance(product, orderDetail.getCount())) {
                throw new ProductBalanceNotEnoughException(product.getName(), orderDetail.getCount());
            }
            usedProductsCounts.put(product.getId(), orderDetail.getCount());
            dbOrderItems.add(OrderItem.builder()
                    .count(orderDetail.getCount())
                    .referredProduct(product)
                    .build());
            totalPrice += product.getPrice() * orderDetail.getCount();
        }
        Customer customer = customerService.findByName(createOrderRequestDto.getCustomerName());
        if (totalPrice <= customer.getCreditLimit() - customer.getCurrentCredit()) {
            customerService.addCustomerCredit(customer, totalPrice);
            for (var x : usedProductsCounts.entrySet()) {
                productService.deductBalance(x.getKey(), x.getValue());
            }
            Order order = Order.builder()
                    .referredCustomer(customer)
                    .currentOrderItems(new ArrayList<>())
                    .totalPrice(totalPrice)
                    .build();
            for (OrderItem x : dbOrderItems) {
                order.addOrderItem(x);
            }
            orderRepo.save(order);
            return "Added";
        } else
            throw new CustomerCreditNotEnoughException(customer.getName(), totalPrice, customer.getCurrentCredit());
    }

    private boolean validateProductBalance(Product product, Integer count) {
        return product.getBalance() >= count;
    }

    public String delete(Long id) {
        orderRepo.delete(orderRepo.getById(id));
        return "Deleted";
    }
}
