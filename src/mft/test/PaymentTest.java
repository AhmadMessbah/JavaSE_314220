package mft.test;

import mft.model.entity.Payment;

public class PaymentTest {
    public static void main(String[] args) {
        Payment payment =
                Payment
                        .builder()
                        .id(1)
                        .build();

        System.out.println(payment.toCsv());

    }
}
