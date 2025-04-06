package com.sideproject.qrordermanager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU_ORDER_OPTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOrderOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OO_ID")
    private Long ooId;

    @ManyToOne
    @JoinColumn(name = "OO_MO_ID", nullable = false)
    private MenuOrder ooMo;

    @ManyToOne
    @JoinColumn(name = "OO_OP_ID", nullable = false)
    private MenuOption ooOp;

    @Builder
    public MenuOrderOption (MenuOrder ooMo,
                            MenuOption ooOp) {
        this.ooMo = ooMo;
        this.ooOp = ooOp;
    }

}
