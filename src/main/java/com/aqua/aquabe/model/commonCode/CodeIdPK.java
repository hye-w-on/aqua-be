package com.aqua.aquabe.model.commonCode;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class CodeIdPK implements Serializable {

    @Column(name = "COMMON_CODE_ID")
    private String commonCodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_CODE_ID")
    private GroupCodeEntity groupCode;

}
