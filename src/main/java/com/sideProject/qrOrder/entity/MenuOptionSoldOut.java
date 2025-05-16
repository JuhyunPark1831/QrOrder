package com.sideProject.qrOrder.entity;

import com.sideProject.qrOrder.entity.Common.BaseEntity;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutProgressStatus;
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

    @Column(name = "MS_PROCESSED", nullable = false)
    private SoldOutProgressStatus osStatus;

    @ManyToOne
    @JoinColumn(name = "OS_OP_ID")
    private MenuOption osOp;

    @Builder
    public MenuOptionSoldOut(LocalDateTime osStart,
                             LocalDateTime osEnd,
                             SoldOutProgressStatus osStatus,
                             MenuOption osOp) {
        this.osStart = osStart;
        this.osEnd = osEnd;
        this.osStatus = osStatus;
        this.osOp = osOp;
    }

    public void modifyOsStatus(SoldOutProgressStatus osStatus) {
        this.osStatus = osStatus;
    }
}
