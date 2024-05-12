package com.aqua.aquabe.model.reservation;

import java.util.List;

import com.aqua.aquabe.model.common.DefaultVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@SuperBuilder
public class ReservationRequestVO extends DefaultVO {

    @Schema(description = "예약번호", example = "1", hidden = true)
    private String reservationNo;

    @Schema(description = "서비스ID", example = "서비스ID")
    private String serviceId;

    @Schema(description = "서비스사용일시", example = "서비스사용일시")
    // @DateTimeFormat(pattern = "yyyyMMddHHmm") // @RequestParam, @ModelAttribute
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm") // @RequestBody, @ResponseBody
    // java8부터 추가
    private LocalDateTime serviceDate;

    @Schema(description = "예약상태", example = "PENDING")
    private String status;

    @Schema(description = "예약상세리스트")
    List<ReservationDetailVO> reservationDetailList;

}