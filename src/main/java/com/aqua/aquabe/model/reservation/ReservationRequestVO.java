package com.aqua.aquabe.model.reservation;

import java.util.List;

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
public class ReservationRequestVO {

    @Schema(description = "예약번호", example = "1", hidden = true)
    private String reservationNo;

    @Schema(description = "서비스ID", example = "서비스ID")
    private String serviceId;

    @Schema(description = "서비스사용일자", example = "서비스사용일자")
    private String serviceDate;

    @Schema(description = "예약상태", example = "PENDING")
    private String status;

    @Schema(description = "예약상세리스트")
    List<ReservationDetailVO> reservationDetailList;

}