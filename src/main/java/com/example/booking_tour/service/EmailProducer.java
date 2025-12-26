package com.example.booking_tour.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.booking_tour.config.RabbitMQConfig;
import com.example.booking_tour.dto.EmailMessage;

/**
 * Email producer service to send email messages to RabbitMQ queue.
 */
@Service
public class EmailProducer {

    private static final Logger logger = LoggerFactory.getLogger(EmailProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send email message to RabbitMQ queue.
     * 
     * @param message EmailMessage needs to send
     */
    public void sendEmailMessage(EmailMessage message) {
        logger.info("Đang gửi email message vào queue cho: {}", message.to());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EMAIL_EXCHANGE,
                RabbitMQConfig.EMAIL_ROUTING_KEY,
                message);
        logger.info("Email message đã được đẩy vào queue thành công");
    }
}
