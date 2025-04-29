package com.sideProject.qrOrder.entity;

import com.sideProject.qrOrder.entity.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_MENU_OPTION_SOLD_OUT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionSoldOut extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OS_ID")
    private Long osId;

    @Column(name = "OS_START", nullable = false)
    private LocalDateTime osStart;

    @Column(name = "OS_END", nullable = false)
    private LocalDateTime osEnd;

    @ManyToOne
    @JoinColumn(name = "OS_OP_ID")
    private MenuOption osOp;

    @Builder
    public MenuOptionSoldOut(LocalDateTime osStart,
                             LocalDateTime osEnd,
                             MenuOption osOp) {
        this.osStart = osStart;
        this.osEnd = osEnd;
        this.osOp = osOp;
    }
}
