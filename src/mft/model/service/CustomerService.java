package mft.model.service;

import mft.model.entity.Customer;
import mft.model.respository.CustomerRepository;

import java.util.List;

//Bussiness Logic:
public class CustomerService {
    public static void save(Customer customer) throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            repository.save(customer);
        }
    }

    public static void edit(Customer customer) throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            repository.edit(customer);
        }
    }

    public static void remove(int id) throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            repository.remove(id);
        }
    }

    public static List<Customer> findAll() throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            return repository.findAll();
        }
    }

    public static Customer findById(int id) throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            return repository.findById(id);
        }
    }

    public static List<Customer> findByName(String name) throws Exception {
        try (CustomerRepository repository = new CustomerRepository()) {
            return repository.findByName(name);
        }
    }
}
