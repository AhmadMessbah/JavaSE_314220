package mft.test;

import mft.model.entity.Contact;

public class ContactTest {
    public static void main(String[] args) {
        Contact contact =
                Contact
                        .builder()
                        .id(1)
                        .state("Tehran")
                        .build();

        System.out.println(contact.toCsv());
    }
}
