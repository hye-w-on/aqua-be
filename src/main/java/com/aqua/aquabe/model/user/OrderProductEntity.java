package com.aqua.aquabe.model.user;

import com.aqua.aquabe.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//Entity
@Table
public class OrderProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    //private Long orderId;
    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private OrderEntity order;

    //private Long productId;
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private ProductEntity product;
}
