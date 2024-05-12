package com.aqua.aquabe.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class StatusEnumConstants {
    // Case 1
    public enum MemberAccountStatus {
        // 멤버 계정상태
        ACTIVE("활성"),
        INACTIVE("비활성"),
        SUSPENDED("일시정지"),
        CLOSED("정지");

        private final String label;

        private MemberAccountStatus(String label) {
            // enum에서 생성자 같은 역할, Enum 클래스는 생성자가 있다고 하더라도 new 연산으로 생성할 수 없음
            this.label = label;
        }

        public String getLabel() {
            // getter
            return label;
        }
    }

    // Case 2 : lombok을 사용하여 대체
    @Getter
    @RequiredArgsConstructor
    public enum ReservationStatus {
        // 예약상태
        PENDING("대기"),
        CONFIRMED("확정"),
        CANCELED("취소"),
        COMPLETED("완료"),
        NO_SHOW("노쇼");

        private final String label;
    }
}
