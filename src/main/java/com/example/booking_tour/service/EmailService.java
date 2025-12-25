package com.example.booking_tour.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendBookingSuccessEmail(
            String to,
            String bookingCode,
            String tourName
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Booking thành công 🎉");
        message.setText("""
            Xin chào,

            Booking của bạn đã được xác nhận thành công!

            Mã booking: %s
            Tour: %s

            Cảm ơn bạn đã sử dụng dịch vụ.
            """.formatted(bookingCode, tourName));

        mailSender.send(message);
    }
}
