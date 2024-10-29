package mft.model.service;

import mft.model.entity.Customer;
import mft.model.respository.CustomerRepository;

//Bussiness Logic:
public class CustomerService {
    public static void save(Customer customer) throws Exception {
        CustomerRepository repository = new CustomerRepository();
        repository.save(customer);
    }

    public static void edit(Customer customer) throws Exception {
        CustomerRepository repository = new CustomerRepository();
        repository.edit(customer);
    }

    public static void remove(Customer customer) throws Exception {
        CustomerRepository repository = new CustomerRepository();
        repository.remove(customer);
    }
}
