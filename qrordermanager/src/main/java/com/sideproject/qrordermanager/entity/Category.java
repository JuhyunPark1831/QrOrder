package com.sideproject.qrordermanager.entity;

import jakarta.persistence.*;
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

    @Column(name = "CA_NAME", unique = true, nullable = false)
    private String caName;

    @Builder
    public Category (String caName) {
        this.caName = caName;
    }
}
