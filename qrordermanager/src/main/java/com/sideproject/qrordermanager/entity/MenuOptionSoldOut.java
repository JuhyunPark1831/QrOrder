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

    @Builder
    public MenuOptionSoldOut(LocalDateTime osStart,
                             LocalDateTime osEnd) {
        this.osStart = osStart;
        this.osEnd = osEnd;
    }
}
