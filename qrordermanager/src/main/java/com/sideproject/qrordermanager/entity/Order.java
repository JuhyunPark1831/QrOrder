package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import com.sideproject.qrordermanager.entity.Common.ENUM.OrderPayment;
import com.sideproject.qrordermanager.entity.Common.ENUM.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OR_ID")
    private Long orId;

    @Column(name = "OR_UUID", unique = true)
    private String orUuid;

    @Column(name = "OR_STATUS", nullable = false)
    private OrderStatus orStatus;

    @Column(name = "OR_PAYMENT", nullable = false)
    private OrderPayment orPayment;

    @Column(name = "OR_PHONE", nullable = false)
    private String orPhone;

    @Builder
    public Order (String orUuid,
                  OrderStatus orderStatus,
                  OrderPayment orderPayment,
                  String orPhone) {
        this.orUuid = orUuid;
        this.orStatus = orderStatus;
        this.orPayment = orderPayment;
        this.orPhone = orPhone;
    }
}
