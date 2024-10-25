package mft.model.service;

import mft.model.entity.Customer;
import mft.model.respository.CustomerRepository;

import java.io.IOException;
import java.util.List;

//Bussiness Logic:
public class CustomerService {
    public static void saveAll(List<Customer> customerList) throws IOException {
        CustomerRepository repository = new CustomerRepository();
        repository.saveAll(customerList);
    }

    public static List<Customer> findAll() throws Exception {
        CustomerRepository repository = new CustomerRepository();
        return repository.findAll();
    }
}
