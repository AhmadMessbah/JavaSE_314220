package mft.model.service;

import mft.model.entity.Payment;
import mft.model.respository.PaymentRepository;

import java.util.List;

public class PaymentService {

    public static void save(Payment payment) throws Exception {
        try(PaymentRepository paymentRepository = new PaymentRepository()){
        paymentRepository.save(payment);}
    }

    public static void edit(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.edit(payment);}
    }

    public static void remove(int id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.remove(id);}
    }

    public static List<Payment> findAll() throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findAll();}
    }

/**    public static Payment findById(int id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findById(id);}
    }**/

    public static List<Payment> findByName(String name) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return PaymentRepository.findByName(name);
        }
    }
}
