package com.aqua.aquabe.model.reservation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class ReservationDetailVO {

    @Schema(description = "예약상세번호", example = "1", hidden = true)
    private String reservationDetailNo;

    @Schema(description = "예약번호", example = "1", hidden = true)
    private String reservationNo;

    @Schema(description = "예약순서", example = "1", hidden = true)
    private int orderNo;

    @Schema(description = "서비스사용일자", example = "서비스사용일자")
    private String serviceDate;

    @Schema(description = "예약상세상태", example = "PENDING")
    private String status;

}