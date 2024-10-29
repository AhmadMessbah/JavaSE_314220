package mft.model.service;

import mft.model.entity.Product;
import mft.model.respository.ProductRepository;

import java.util.List;

public class ProductService {
    public static void save(Product product) throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            repository.save(product);
        }
    }

    public static void edit(Product product) throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            repository.edit(product);
        }
    }

    public static void remove(int id) throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            repository.remove(id);
        }
    }


    public static List<Product> findAll() throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            return repository.findAll();
        }
    }

    public static Product findById(int id) throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            return repository.findById(id);
        }
    }

    public static Product findByName(String name) throws Exception {
        try (ProductRepository repository = new ProductRepository()) {
            return repository.findByName(name);
        }
    }
}
