package com.example.booking_tour.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.booking_tour.dto.EmailMessage;

/**
 * Email service to send email through RabbitMQ message queue.
 * Email will be pushed into queue and processed asynchronously by
 * EmailConsumer.
 */
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailProducer emailProducer;

    public EmailService(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

    /**
     * Send booking success email.
     * Email will be pushed into queue and processed asynchronously by
     * EmailConsumer.
     * 
     * @param to          email address of recipient
     * @param bookingCode booking code
     */
    public void sendBookingSuccessEmail(String to, String bookingCode, String tourName) {
        logger.info("Đang tạo email booking success cho: {}", to);

        EmailMessage message = EmailMessage.bookingSuccess(to, bookingCode, tourName);
        emailProducer.sendEmailMessage(message);

        logger.info("Email booking success đã được đẩy vào queue");
    }
}
