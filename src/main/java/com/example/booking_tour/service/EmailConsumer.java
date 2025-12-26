package com.example.booking_tour.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.booking_tour.config.RabbitMQConfig;
import com.example.booking_tour.dto.EmailMessage;

/**
 * Email consumer service to receive email messages from RabbitMQ queue and send
 * email.
 */
@Service
public class EmailConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    private final JavaMailSender mailSender;

    public EmailConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Listen and process email messages from queue.
     * 
     * @param message EmailMessage received from queue
     */
    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void consumeEmailMessage(EmailMessage message) {
        logger.info("Nhận được email message từ queue cho: {}", message.to());

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(message.to());
            mailMessage.setSubject(message.subject());
            mailMessage.setText(message.body());

            mailSender.send(mailMessage);
            logger.info("Email đã được gửi thành công đến: {}", message.to());
        } catch (Exception e) {
            logger.error("Lỗi khi gửi email đến {}: {}", message.to(), e.getMessage());
            throw e; // Re-throw để RabbitMQ xử lý retry nếu cấu hình
        }
    }
}
