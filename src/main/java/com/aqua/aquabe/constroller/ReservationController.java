package com.aqua.aquabe.constroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aqua.aquabe.constants.CommonConstants;
import com.aqua.aquabe.constants.StatusCodeConstants;
import com.aqua.aquabe.model.common.CommonResponseVO;
import com.aqua.aquabe.model.reservation.ReservationRequestVO;
import com.aqua.aquabe.model.reservation.ReservationResponseVO;
import com.aqua.aquabe.service.admin.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reservation")
@Validated
public class ReservationController {
        private final ReservationService reservationService;

        @Operation(summary = "예약 조회")
        @GetMapping(value = "/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<List<ReservationResponseVO>>> getReservations() {
                return new ResponseEntity<>(CommonResponseVO.<List<ReservationResponseVO>>builder()
                                .successOrNot(CommonConstants.YES)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(reservationService.getReservations())
                                .build(), HttpStatus.OK);
        }

        @Operation(summary = "1건 예약")
        @PostMapping(value = "/v1/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<Integer>> createReservation(
                        @RequestBody ReservationRequestVO reservationRequest) {
                return new ResponseEntity<>(CommonResponseVO.<Integer>builder()
                                .successOrNot(CommonConstants.YES)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(reservationService.createReservation(reservationRequest))
                                .build(), HttpStatus.OK);
        }

        @Operation(summary = "예약 상태 수정")
        @PutMapping(value = "/v1/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<Integer>> modifyReservation(
                        @RequestBody ReservationResponseVO reservation) {
                return new ResponseEntity<>(CommonResponseVO.<Integer>builder()
                                .successOrNot(CommonConstants.YES)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(reservationService.modifyReservation(reservation))
                                .build(), HttpStatus.OK);
        }

        @Operation(summary = "예약 삭제")
        @PatchMapping(value = "/v1/reservation/{reservationNo}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CommonResponseVO<Integer>> modifyDisableReservation(
                        @PathVariable @NotBlank String reservationNo) {
                return new ResponseEntity<>(CommonResponseVO.<Integer>builder()
                                .successOrNot(CommonConstants.YES)
                                .statusCode(StatusCodeConstants.SUCCESS)
                                .data(reservationService.modifyDisableReservation(reservationNo))
                                .build(), HttpStatus.OK);
        }

}
