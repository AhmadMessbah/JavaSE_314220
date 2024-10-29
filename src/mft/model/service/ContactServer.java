package mft.model.service;

import mft.model.entity.Contact;
import mft.model.respository.ContactRepository;

public class ContactServer {
    public static class ContactService {
        public static void save(Contact Contact) throws Exception {
            ContactRepository repository = new ContactRepository();
            repository.save(Contact);
        }

        public static void edit(Contact Contact) throws Exception {
            ContactRepository repository = new ContactRepository();
            repository.edit(Contact);
        }

        public static void remove(int id) throws Exception {
            ContactRepository repository = new ContactRepository();
            repository.remove(id);
        }
}}
