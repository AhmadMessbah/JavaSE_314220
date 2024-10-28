package mft.model.respository;

import mft.model.entity.Payment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentRepository {
    public void saveAll(List<Payment> paymentList) throws IOException {
        File file = new File("payments.csv");
        file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("id,title,price,amount,dateTime,description,paymentType");
        for (Payment payment : paymentList) {
            fileWriter.write(payment.toCsv());
        }
        fileWriter.close();
    }
//todo
    public List<Payment> findAll() throws Exception {
        File file = new File("payments.csv");
        Scanner scanner = new Scanner(file);
        List<Payment> paymentList = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Payment payment = new Payment(line);
            paymentList.add(payment);
        }
        return paymentList;
    }
}
