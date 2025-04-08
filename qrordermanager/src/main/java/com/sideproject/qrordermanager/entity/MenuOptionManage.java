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
@Table(name = "TB_MENU_OPTION_MANAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionManage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OM_ID")
    private Long omId;

    @Column(name = "OM_START", nullable = false)
    private LocalDateTime omStart;

    @Column(name = "OM_END", nullable = false)
    private LocalDateTime omEnd;

    @Builder
    public MenuOptionManage (LocalDateTime omStart,
                             LocalDateTime omEnd) {
        this.omStart = omStart;
        this.omEnd = omEnd;
    }
}
