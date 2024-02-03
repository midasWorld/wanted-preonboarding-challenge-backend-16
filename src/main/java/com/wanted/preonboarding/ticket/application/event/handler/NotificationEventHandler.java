package com.wanted.preonboarding.ticket.application.event.handler;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.wanted.preonboarding.ticket.application.event.ReservationCancelledEvent;
import com.wanted.preonboarding.ticket.domain.entity.ReservationCancelNotice;
import com.wanted.preonboarding.ticket.infrastructure.repository.ReservationCancelNoticeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventHandler {
	private final ReservationCancelNoticeRepository reservationCancelNoticeRepository;

	@Async
	@EventListener
	public void onReservationCancelled(ReservationCancelledEvent event) {

		List<ReservationCancelNotice> notices = reservationCancelNoticeRepository.findAllByPerformanceId(event.getPerformanceId());
		notices.forEach(notice -> {
			sendNotification(notice.getPhoneNumber(), notice.getName(), event.getPerformanceName());
		});
	}

	private void sendNotification(String phoneNumber, String name, String performanceName) {
		// TODO 알림 보내기(To. PhoneNumber, Message: 공연명, 이름)
		log.info(phoneNumber + MessageFormat.format(" → 안녕하세요. {0}님 {1} 공연의 예약 취소 좌석이 생겼습니다.", name, performanceName));
	}
}
