package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import com.sideproject.qrordermanager.entity.Common.ENUM.MenuStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MENU")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MN_ID")
    private Long mnId;

    @Column(name = "MN_NAME", nullable = false)
    private String mnName;

    @Column(name = "MN_PRICE", nullable = false)
    private int mnPrice;

    @Column(name = "MN_STATUS", nullable = false)
    private MenuStatus mnStatus;

    @Column(name = "MN_DESCRIPTION")
    private String mnDescription;

    @Column(name = "MN_IMAGE_PATH")
    private String mnImagePath;

    @Builder
    public Menu (Long mnId,
                String mnName,
                int mnPrice,
                MenuStatus menuStatus,
                String mnDescription,
                String mnImagePath) {
        this.mnId = mnId;
        this.mnName = mnName;
        this.mnPrice = mnPrice;
        this.mnStatus = menuStatus;
        this.mnDescription = mnDescription;
        this.mnImagePath = mnImagePath;
    }
}
