package com.aqua.aquabe.constants;

import lombok.Getter;

@Getter
public class YnConstants {

    public static final String Y = "Y";
    public static final String N = "N";

    public enum UseType {
        // 사용여부
        Y("YES", 1, true),
        N("NO", 0, false),
        D("DELETE", -1, false);

        private final String UseTypeLabel;
        private final int UseTypeNumber;
        private final boolean UseTypeBoolean;

        private UseType(String UseTypeLabel, int UseTypeNumber, boolean UseTypeBoolean) {
            this.UseTypeLabel = UseTypeLabel;
            this.UseTypeNumber = UseTypeNumber;
            this.UseTypeBoolean = UseTypeBoolean;
        }

    }

}
