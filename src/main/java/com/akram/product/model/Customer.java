package com.akram.product.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customers")
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;


    @Column(name = "creditLimit")
    private Long creditLimit;
    @Column(name = "currentCredit")
    private Long currentCredit;

    @OneToMany(mappedBy = "referredCustomer", fetch = LAZY,cascade = CascadeType.ALL)
    private List<Order> orders;
}
