package mft.model.service;

import mft.model.entity.Payment;
import mft.model.respository.PaymentRepository;

public class PaymentService  {

    public static void save(Payment payment) throws Exception {
        PaymentRepository paymentRepository = new PaymentRepository();
        paymentRepository.save(payment);

    }

    public static void edit(Payment payment) throws Exception {
        PaymentRepository paymentRepository = new PaymentRepository();
        paymentRepository.edit(payment);
    }

    public static void delete(int id) throws Exception {
        PaymentRepository paymentRepository = new PaymentRepository();
        paymentRepository.delete(id);
    }
}
