package com.aqua.aquabe.model.commonCode;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "group_code")
public class GroupCodeEntity {
    @Id
    private String groupCodeId;

    private String groupCodeName;

    @OneToMany(mappedBy = "codeIdPK.groupCode", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommonCodeEntity> commonCodes = new ArrayList<>();

    public void addCommonCodes(CommonCodeEntity commonCode) {
        commonCode.setCodeIdPK(new CodeIdPK(commonCode.getCodeIdPK().getCommonCodeId(), this));
        commonCodes.add(commonCode);
    }

}
