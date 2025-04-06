package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import com.sideproject.qrordermanager.entity.Common.ENUM.MenuStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ME_ID")
    private Long meId;

    @Column(name = "ME_NAME", unique = true, nullable = false)
    private String meName;

    @Column(name = "ME_PRICE", nullable = false)
    private int mePrice;

    @Column(name = "ME_STATUS", nullable = false)
    private MenuStatus meStatus;

    @Column(name = "ME_DESCRIPTION")
    private String meDescription;

    @Column(name = "ME_IMAGE_PATH")
    private String meImagePath;

    @ManyToOne
    @JoinColumn(name = "ME_CA_ID", nullable = false)
    private Category meCa;

    @Builder
    public Menu (String meName,
                 int mePrice,
                 MenuStatus meStatus,
                 String meDescription,
                 String meImagePath,
                 Category meCa) {
        this.meName = meName;
        this.mePrice = mePrice;
        this.meStatus = meStatus;
        this.meDescription = meDescription;
        this.meImagePath = meImagePath;
        this.meCa = meCa;
    }
}