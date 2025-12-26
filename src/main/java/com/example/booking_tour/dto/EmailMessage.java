package com.example.booking_tour.dto;

import java.io.Serializable;

/**
 * DTO represents email message in RabbitMQ queue.
 */
public record EmailMessage(
        String to,
        String subject,
        String body) implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Factory method to create booking success email message.
     */
    public static EmailMessage bookingSuccess(String to, String bookingCode, String tourName) {
        String subject = "Booking thành công 🎉";
        String body = """
                Xin chào,

                Booking của bạn đã được xác nhận thành công!

                Mã booking: %s
                Tour: %s

                Cảm ơn bạn đã sử dụng dịch vụ.
                """.formatted(bookingCode, tourName);

        return new EmailMessage(to, subject, body);
    }
}
