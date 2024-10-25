package mft.model.respository;

import mft.model.entity.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository {
    public void saveAll(List<Customer> customerList) throws IOException {
        File file = new File("customers.csv");
        file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("id, name, familyName, username, password, phone, active");
        for (Customer customer : customerList) {
            fileWriter.write(customer.toCsv() + "\n");
        }
        fileWriter.close();
    }

    public List<Customer> findAll() throws Exception {
        File file = new File("customers.csv");
        Scanner scanner = new Scanner(file);

        List<Customer> customerList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Customer customer = new Customer(line);
            customerList.add(customer);
        }
        return customerList;
    }
}
