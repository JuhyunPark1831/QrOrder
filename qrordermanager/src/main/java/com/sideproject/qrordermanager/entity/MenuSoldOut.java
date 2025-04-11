package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
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

    @ManyToOne
    @JoinColumn(name = "MS_ME_ID", nullable = false)
    private Menu msMe;

    @Builder
    public MenuSoldOut(LocalDateTime msStart,
                       LocalDateTime msEnd,
                       Menu msMe) {
        this.msStart = msStart;
        this.msEnd = msEnd;
        this.msMe = msMe;
    }
}
