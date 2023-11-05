package com.aqua.aquabe.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.aquabe.model.reservation.ReservationDetailVO;
import com.aqua.aquabe.model.reservation.ReservationRequestVO;
import com.aqua.aquabe.model.reservation.ReservationResponseVO;
import com.aqua.aquabe.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseVO> getReservations() {

        List<ReservationResponseVO> reservationList = reservationRepository.selectReservations();

        return reservationList;
    }

    @Override
    public int modifyReservation(ReservationResponseVO reservation) {
        return reservationRepository.updateReservation(reservation);
    }

    @Override
    public int modifyDisableReservation(String reservationNo) {
        return reservationRepository.updateDisableReservation(reservationNo);
    }

    @Override
    public int createReservation(ReservationRequestVO reservationRequest) {

        reservationRepository.insertReservation(reservationRequest);
        String reservationNo = reservationRequest.getReservationNo(); // useGeneratedKeys 설정 시 가능
        for (int i = 0; i < reservationRequest.getReservationDetailList().size(); i++) {
            ReservationDetailVO reservationDetail = reservationRequest.getReservationDetailList().get(i);
            reservationDetail.setReservationNo(reservationNo);
            reservationDetail.setOrderNo(i);
            reservationRepository.insertReservationDetail(reservationDetail);
        }

        return 1; // TODO
    }
}
