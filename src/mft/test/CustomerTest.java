package mft.test;

import mft.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args) {
        Customer customer =
                Customer
                        .builder()
                        .id(1)
                        .name("babak")
                        .familyName("oveisi")
                        .username("admin")
                        .password("admin")
                        .phone("09356207032")
                        .active(true)
                        .build();

        System.out.println(customer.toCsv());


        Customer c1 = new Customer(customer.toCsv());
        System.out.println(c1);
    }
}
