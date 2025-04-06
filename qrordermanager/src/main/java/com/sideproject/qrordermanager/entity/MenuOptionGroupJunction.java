package com.sideproject.qrordermanager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU_OPTION_GROUP_JUNCTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroupJunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MJ_ID")
    private Long mjId;

    @Column(name = "MJ_SEQ", unique = true)
    private int mjSeq;

    @ManyToOne
    @JoinColumn(name = "MJ_OG_ID", nullable = false)
    private MenuOptionGroup mjOg;

    @ManyToOne
    @JoinColumn(name = "MJ_ME_ID", nullable = false)
    private Menu mjMe;

    @Builder
    public MenuOptionGroupJunction (int mjSeq,
                                    MenuOptionGroup mjOg,
                                    Menu mjMe) {
        this.mjSeq = mjSeq;
        this.mjOg = mjOg;
        this.mjMe = mjMe;
    }
}
