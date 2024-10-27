package mft.model.service;

import mft.model.entity.Product;
import mft.model.respository.ProductRepository;

// Business Logic
public class ProductService {
    public static void save(Product product) throws Exception {
        ProductRepository repository = new ProductRepository();
        repository.save(product);
    }

    public static void edit(Product product) throws Exception {
        ProductRepository repository = new ProductRepository();
        repository.edit(product);
    }

    public static void remove(int id) throws Exception {
        ProductRepository repository = new ProductRepository();
        repository.remove(id);
    }


//    public static List<Product > findAll() throws Exception {
//        ProductRepository repository = new ProductRepository();
//        return null; // repository.findAll();
//    }
}
