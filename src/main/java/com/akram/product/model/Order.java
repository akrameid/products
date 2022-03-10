package com.akram.product.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@Builder(toBuilder = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;

    @ManyToOne( fetch = LAZY)
    @JoinColumn(name = "customer_id" )
    private Customer referredCustomer;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> currentOrderItems =new ArrayList<OrderItem>();

    public void addOrderItem(OrderItem orderItem){
        currentOrderItems.add(orderItem);
        orderItem.setReferredOrder(this);
    }
    public void removeOrderItem(OrderItem orderItem){
        currentOrderItems.remove(orderItem);
        orderItem.setReferredOrder(null);
    }
}
