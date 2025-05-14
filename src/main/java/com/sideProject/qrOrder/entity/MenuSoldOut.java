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
@Table(name = "TB_MENU_SOLD_OUT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuSoldOut extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MS_ID")
    private Long msId;

    @Column(name = "MS_START", nullable = false)
    private LocalDateTime msStart;

    @Column(name = "MS_END", nullable = false)
    private LocalDateTime msEnd;

    @Column(name = "MS_PROCESSED", nullable = false)
    private SoldOutProgressStatus msStatus;

    @ManyToOne
    @JoinColumn(name = "MS_ME_ID", nullable = false)
    private Menu msMe;

    @Builder
    public MenuSoldOut(LocalDateTime msStart,
                       LocalDateTime msEnd,
                       SoldOutProgressStatus msStatus,
                       Menu msMe) {
        this.msStart = msStart;
        this.msEnd = msEnd;
        this.msStatus = msStatus;
        this.msMe = msMe;
    }

    public void modifyMsStatus(SoldOutProgressStatus meStatus) {
        this.msStatus = meStatus;
    }
}
