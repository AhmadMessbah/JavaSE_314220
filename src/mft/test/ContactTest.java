package mft.test;

import mft.model.entity.Contact;

public class ContactTest {
    public static void main(String[] args) {
        Contact contact =
                Contact
                        .builder()
                        .id(1)
                        .state("Tehran")
                        .city("Tehran")
                        .region("1")
                        .address("andarzgo")
                        .postalCode("777")
                        .phone("0912")
                        .description("moshakhasat")
                        .build();

        System.out.println(contact.toCsv());
    }
}
