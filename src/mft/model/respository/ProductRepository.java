package mft.model.respository;

import mft.model.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductRepository {
    public void saveAll(List<Product> productList) throws IOException {
        File file = new File("product.csv");
        file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("id,name,price,quantity,category,expireDate,discount,catalogue,image,transaction_type");
        for (Product product : productList) {
            fileWriter.write(product.toCsv());
        }
        fileWriter.close();
    }

    public List<Product> findAll() throws Exception {
        File file = new File("product.csv");
        Scanner scanner = new Scanner(file);

        List<Product> productList = new ArrayList<>();
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Product product = new Product(line);
            productList.add(product);
        }
        return productList;
    }
}
