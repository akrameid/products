package com.akram.product.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orderitems")
@Builder(toBuilder = true)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "product_id")
//    @RestResource(path = "libraryAddress", rel="address")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
