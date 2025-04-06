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
@Table(name = "TB_MENU_MANAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuManage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MM_ID")
    private Long mmId;

    @Column(name = "MM_START", nullable = false)
    private LocalDateTime mmStart;

    @Column(name = "MM_END", nullable = false)
    private LocalDateTime mmEnd;

    @ManyToOne
    @JoinColumn(name = "MM_ME_ID", nullable = false)
    private Menu mmMe;

    @Builder
    public MenuManage (LocalDateTime mmStart,
                       LocalDateTime mmEnd,
                       Menu mmMe) {
        this.mmStart = mmStart;
        this.mmEnd = mmEnd;
        this.mmMe = mmMe;
    }
}
