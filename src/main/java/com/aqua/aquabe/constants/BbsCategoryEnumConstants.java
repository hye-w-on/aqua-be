package com.aqua.aquabe.constants;

public class BbsCategoryEnumConstants {

    public enum BbsCategoryCode {

        NOTICE("공지"),
        FAQ("FAQ");

        private final String label;

        private BbsCategoryCode(String label) { // enum에서 생성자 같은 역할
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
