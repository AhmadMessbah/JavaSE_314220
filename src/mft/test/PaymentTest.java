package mft.test;

import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentTest {
    public static void main(String[] args) {
        Payment payment =
                Payment
                        .builder()
                        .id(1)
                        .title("Mobile")
                        .price(300)
                        .amount(3)
                        .dateTime(String.valueOf(LocalDateTime.now()))
                        .description("iphone16")
                        .paymentType(PaymentType.Cash)
                        .build();

        System.out.println(payment.toCsv());

    }
}
