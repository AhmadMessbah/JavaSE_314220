package mft.test;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Product;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;
import mft.model.service.ProductService;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ProductTest {
//    private static Logger log = Logger.getLogger(ProductTest.class);

    public static void main(String[] args) throws Exception {
//        System.out.println(ProductService.findAll());

        Product product1 =
                Product
                        .builder()
                        .name("Asus 1230")
                        .price(1800)
                        .quantity(3)
                        .discount(0)
                        .category(Category.Electrical)
                        .transactionType(TransactionType.outcome)
                        .expireDate(LocalDate.now())
                        .image(false)
                        .catalogue(true)
                        .build();

        ProductService.save(product1);
        System.out.println(product1);
//        System.out.println(ProductService.findAll());
//        System.out.println(ProductService.findById(2));
//        System.out.println(ProductService.findById(200));

//        log.info("Product Saved");
//        log.error("khata ....");
//        log.debug("App Started");

    }
}
