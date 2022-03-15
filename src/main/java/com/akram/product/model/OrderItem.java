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

    @ManyToOne
    @JoinColumn(name = "product_id")
//    @RestResource(path = "libraryAddress", rel="address")
    private Product referredProduct;

    @Column(name = "count")
    private Integer count;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="order_id")
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order referredOrder;
}
