package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU_OPTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OP_ID")
    private Long opId;

    @Column(name = "OP_NAME", unique = true, nullable = false)
    private String opName;

    @Column(name = "OP_PRICE")
    private int opPrice;

    @ManyToOne
    @JoinColumn(name = "OP_OG_ID", nullable = false)
    private MenuOptionGroup opOg;

    @Builder
    public MenuOption (String opName,
                       int opPrice,
                       MenuOptionGroup opOg) {
        this.opName = opName;
        this.opPrice = opPrice;
        this.opOg = opOg;
    }
}
