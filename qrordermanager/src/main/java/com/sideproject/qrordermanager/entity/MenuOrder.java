package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MO_ID")
    private Long moId;

    @Column(name = "MO_COUNT")
    private int moCount;

    @ManyToOne
    @JoinColumn(name = "MO_ME_ID", nullable = false)
    private Menu moMe;

    @ManyToOne
    @JoinColumn(name = "MO_OR_ID", nullable = false)
    private Order moOr;

    @Builder
    public MenuOrder (int moCount,
                      Menu moMe,
                      Order moOr) {
        this.moCount = moCount;
        this.moMe = moMe;
        this.moOr = moOr;
    }
}
