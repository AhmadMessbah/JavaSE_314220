package mft.model.service;

import mft.model.entity.Contact;
import mft.model.respository.ContactRepository;

import java.util.List;

public class ContactServer {
    public static class ContactService {
        public static void save(Contact Contact) throws Exception {
            try (ContactRepository repository = new ContactRepository()){
                 repository.save(Contact);
         }
        }

        public static void edit(Contact Contact) throws Exception {
            try(ContactRepository repository = new ContactRepository()) {
                repository.edit(Contact);
            }
        }

        public static void remove(int id) throws Exception {
            try(ContactRepository repository = new ContactRepository()) {
                repository.remove(id);
            }
        }

    public static List<Contact> findAll() throws Exception {
        try (ContactRepository repository = new ContactRepository()) {
            return repository.findAll();
        }
    }

    public static Contact findById(int id) throws Exception {
        try (ContactRepository repository = new ContactRepository()) {
            return repository.findById(id);
        }
    }

    public static List<Contact> findByName(String name) throws Exception {
        try (ContactRepository repository = new ContactRepository()) {
            return repository.findByName(name);
        }
    }
}