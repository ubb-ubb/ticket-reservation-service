package com.ticketpaymentservice.service;

import com.ticketpaymentservice.dto.PaymentDto;
import com.ticketpaymentservice.model.Payment;
import com.ticketpaymentservice.model.PaymentStatus;
import com.ticketpaymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Boolean PAYMENT_STATUS = true;

    public PaymentDto checkPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        paymentRepository.save(payment);
        return paymentDto;
    }

    public Boolean checkPayment(Payment payment) {


        if (PAYMENT_STATUS) {
            log.info("Payment successful!");
            paymentRepository.save(payment);
            return true;
        } else if (!PAYMENT_STATUS) {
            log.info("Payment failed!");
            payment.setPaymentStatus(PaymentStatus.FAIL);
            paymentRepository.save(payment);
            return false;
        }

        return false;

    }


}
