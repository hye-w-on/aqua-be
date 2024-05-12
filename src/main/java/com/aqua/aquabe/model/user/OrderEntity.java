package com.aqua.aquabe.model.user;

import com.aqua.aquabe.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Entity
@Table
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    //private Long userNo;
    //컬럼과 객체 구분은 어떻게하는가?
    @ManyToOne
    @JoinColumn(name="USER_NO")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    private List<OrderProductEntity> orderProducts;

    private Date orderDate;
    private Date orderStatus;

}
