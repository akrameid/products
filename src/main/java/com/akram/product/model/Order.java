package com.akram.product.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
@Builder(toBuilder = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.PRIVATE)
    private long id;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "customer_id")
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer referredCustomer;

    @Column(name = "totalPrice")
    private Long totalPrice;

    //    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "referredOrder",cascade = CascadeType.ALL)
    private List<OrderItem> currentOrderItems = new ArrayList<OrderItem>();

    public void addOrderItem(OrderItem orderItem) {
        currentOrderItems.add(orderItem);
        orderItem.setReferredOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        currentOrderItems.remove(orderItem);
        orderItem.setReferredOrder(null);
    }
}
