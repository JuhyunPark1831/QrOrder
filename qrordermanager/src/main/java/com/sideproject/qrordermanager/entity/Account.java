package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ACCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AC_ID")
    private Long acId;

    @Column(name = "AC_LOGIN_ID", nullable = false)
    private String acLoginId;

    @Column(name = "AC_PASSWORD", nullable = false)
    private String acPassword;

    @Column(name = "AC_NAME", nullable = false)
    private String acName;

    @Builder
    public Account (Long acId,
                    String acLoginId,
                    String acPassword,
                    String acName) {
        this.acId = acId;
        this.acLoginId = acLoginId;
        this.acPassword = acPassword;
        this.acName = acName;
    }
}
