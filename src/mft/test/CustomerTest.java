package mft.test;

import mft.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args) {
        Customer customer =
                Customer
                        .builder()
                        .id()
                        .build();

        System.out.println(customer.toCsv());


        Customer c1 = new Customer();
        System.out.println(c1);
    }
}
