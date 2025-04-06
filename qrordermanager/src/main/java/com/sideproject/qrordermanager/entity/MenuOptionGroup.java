package com.sideproject.qrordermanager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TB_MENU_OPTION_GROUP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OG_ID")
    private Long ogId;

    @Column(name = "OG_NAME", unique = true, nullable = false)
    private String ogName;

    @Column(name = "OG_MIN_SELECT")
    private int ogMinSelect;

    @Column(name = "OG_MAX_SELECT")
    private int ogMaxSelect;

    @Builder
    public MenuOptionGroup (String ogName,
                            int ogMinSelect,
                            int ogMaxSelect) {
        this.ogName = ogName;
        this.ogMinSelect = ogMinSelect;
        this.ogMaxSelect = ogMaxSelect;
    }
}
