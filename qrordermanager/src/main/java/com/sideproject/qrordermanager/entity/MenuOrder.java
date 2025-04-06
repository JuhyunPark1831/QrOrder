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
    @JoinColumn(name = "MO_MN_ID", nullable = false)
    private Menu moMn;

    @ManyToOne
    @JoinColumn(name = "MO_OR_ID", nullable = false)
    private Order moOr;

    @Builder
    public MenuOrder (Long moId,
                      int moCount,
                      Menu moMn,
                      Order moOr) {
        this.moId = moId;
        this.moCount = moCount;
        this.moMn = moMn;
        this.moOr = moOr;
    }
}
