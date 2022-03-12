package com.akram.product.controller;

import com.akram.product.ProductApplication;
import com.akram.product.dto.CustomerOrderDto;
import com.akram.product.dto.order.NewOrderRequestDto;
import com.akram.product.dto.order.OrderItemDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderControllerTest {

    @Autowired
    private TestRestTemplate template;

    private static String ORDER_ENDPOINT = "http://localhost:2000/orders/";



    @Test
    public void getAllOrders_ValidDataReturned() throws Exception {
        List<OrderItemDto> orders=new ArrayList<>();
        orders.add(OrderItemDto.builder()
                .count(2)
                .productName("Product_1").build());
        NewOrderRequestDto newOrderRequestDto = NewOrderRequestDto.builder()
                .customerName("Akram")
                .orderDetails(orders).build();
        template.postForEntity(ORDER_ENDPOINT, newOrderRequestDto, String.class);

        ResponseEntity<List<CustomerOrderDto>> responseEntity =
                template.exchange(ORDER_ENDPOINT,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<CustomerOrderDto>>() {
                        });
        List<CustomerOrderDto> result = responseEntity.getBody();
        assertEquals(result.size(),1);

        Long totalPrice = result.get(0).getTotalPrice();
        assertEquals(totalPrice, Long.valueOf(20));

    }
}
