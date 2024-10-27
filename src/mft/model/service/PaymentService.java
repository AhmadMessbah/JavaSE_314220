package mft.model.service;

import mft.model.entity.Payment;
import mft.model.respository.PaymentRepository;

import java.util.Collections;
import java.util.List;

public class PaymentService  {
    //todo
    public static void saveAll(List<Payment> payment) throws Exception {
        PaymentRepository paymentRepository = new PaymentRepository();
        List<Payment> paymentList = Collections.emptyList();
        paymentRepository.saveAll(paymentList);

    }

    public static List<Payment> findAll() throws Exception {
        PaymentRepository paymentRepository = new PaymentRepository();
        return paymentRepository.findAll();

    }
}
