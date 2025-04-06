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
    @JoinColumn(name = "MM_MN_ID", nullable = false)
    private Menu mmMn;

    @Builder
    public MenuManage (Long mmId,
                       LocalDateTime mmStart,
                       LocalDateTime mmEnd,
                       Menu mmMn) {
        this.mmId = mmId;
        this.mmStart = mmStart;
        this.mmEnd = mmEnd;
        this.mmMn = mmMn;
    }
}
