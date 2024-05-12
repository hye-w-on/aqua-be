package com.aqua.aquabe.model.commonCode;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "COMMON_CODE")
public class CommonCodeEntity {

        @EmbeddedId
        private CodeIdPK codeIdPK;

        private String commonCodeName;

        /*
         * @Embedded
         * 
         * @AttributeOverrides({
         * 
         * @AttributeOverride(name="startDateTime",
         * column=@Column(name="USE_START_DATETIME")),
         * 
         * @AttributeOverride(name="endDateTime",
         * column=@Column(name="USE_END_DATETIME"))}
         * )
         * private Period usePeriod;
         * 
         * @Embedded
         * 
         * @AttributeOverrides({
         * 
         * @AttributeOverride(name="startDateTime",
         * column=@Column(name="DISUSE_START_DATETIME")),
         * 
         * @AttributeOverride(name="endDateTime",
         * column=@Column(name="DISUSE_END_DATETIME"))}
         * )
         * private Period disusePeriod;
         */

}
