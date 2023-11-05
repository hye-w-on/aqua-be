package com.aqua.aquabe.service.admin;

import java.util.List;

import com.aqua.aquabe.model.reservation.ReservationRequestVO;
import com.aqua.aquabe.model.reservation.ReservationResponseVO;

public interface ReservationService {

    List<ReservationResponseVO> getReservations();

    int modifyReservation(ReservationResponseVO reservation);

    int modifyDisableReservation(String reservationNo);

    int createReservation(ReservationRequestVO reservationRequest);

}
