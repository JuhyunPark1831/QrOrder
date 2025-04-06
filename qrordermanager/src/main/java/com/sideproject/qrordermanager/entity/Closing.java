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
@Table(name = "TB_CLOSING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Closing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CL_ID")
    private Long clId;

    @Column(name = "CL_START", nullable = false)
    private LocalDateTime clStart;

    @Column(name = "CL_END", nullable = false)
    private LocalDateTime clEnd;

    @Builder
    public Closing (LocalDateTime clStart,
                    LocalDateTime clEnd) {
        this.clStart = clStart;
        this.clEnd = clEnd;
    }
}
