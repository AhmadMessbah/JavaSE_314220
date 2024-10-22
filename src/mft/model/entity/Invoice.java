package invoice.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;



import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Invoice {
    private int id;
    private int quantity;
    private int price;
    private String invoiceSerial;
    private String productName;
    private String customerName
    private String invoiceSerial;
    private LocalDate dateTime;

    public Invoice(String data) {
        String[] invoicetData = data.split(",");
        id = Integer.parseInt(productData[0]);
        invoiceSerial = productData[1];
        productName = productData[2];
        quantity = Integer.parseInt(productData[3]);
        price = Integer.parseInt(productData[4]);
        dateTime = LocalDate.parse(productData[5]);
        customerName =  productData[6];
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", id, invoiceSerial, productName, quantity, price, dateTime, customerName);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
