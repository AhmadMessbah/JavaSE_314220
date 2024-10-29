package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.Category;
import mft.model.entity.enums.TransactionType;


import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private Category category;
    private LocalDate expireDate;
    private int discount;
    private boolean catalogue;
    private boolean image;
    private TransactionType transactionType;

    public void setPrice(int price) throws Exception {
        if (price > 0) {
            this.price = price;
        }else{
            throw new Exception("Price Must Be Positive !!!");
        }
    }

    public void setQuantity(int quantity) throws Exception {
        if (quantity >= 0) {
            this.quantity = quantity;
        }else{
            throw new Exception("Quantity Must Be Positive !!!");
        }


    }   @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
