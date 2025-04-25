package com.sideProject.qrOrder.entity;

import com.sideProject.qrOrder.dto.menuOption.MenuOptionDto;
import com.sideProject.qrOrder.entity.Common.BaseEntity;
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

    @Column(name = "OP_NAME", nullable = false)
    private String opName;

    @Column(name = "OP_PRICE")
    private int opPrice;

    @ManyToOne
    @JoinColumn(name = "OP_OG_ID", nullable = false)
    private MenuOptionGroup opOg;

    @Builder
    public MenuOption(String opName,
                       int opPrice,
                       MenuOptionGroup opOg) {
        this.opName = opName;
        this.opPrice = opPrice;
        this.opOg = opOg;
    }

    public void modify(MenuOptionDto requestDto) {
        this.opName = requestDto.getOpName();
        this.opPrice = requestDto.getOpPrice();
    }
}
