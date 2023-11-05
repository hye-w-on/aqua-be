package com.aqua.aquabe.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aqua.aquabe.model.reservation.ReservationDetailVO;
import com.aqua.aquabe.model.reservation.ReservationRequestVO;
import com.aqua.aquabe.model.reservation.ReservationResponseVO;

@Mapper
public interface ReservationRepository {

    List<ReservationResponseVO> selectReservations();

    int updateReservation(@Param("reservation") ReservationResponseVO reservation);

    int updateDisableReservation(String reservationNo);

    int insertReservation(ReservationRequestVO reservationRequest);

    int insertReservationDetail(ReservationDetailVO reservationDetail);
}
