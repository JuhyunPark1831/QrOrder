package com.sideProject.qrOrder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CA_ID")
    private Long caId;

    @NotBlank
    @Column(name = "CA_NAME", unique = true, nullable = false)
    private String caName;

    @Column(name = "CA_SEQ")
    private int caSeq;

    @Builder
    public Category(String caName,
                    int caSeq) {
        this.caName = caName;
        this.caSeq = caSeq;
    }

    public void modifyCaName(String caName) {
        this.caName = caName;
    }

    public void modifyCaSeq(int caSeq) {
        this.caSeq = caSeq;
    }
}
