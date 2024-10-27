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
    private String customerName;
    private LocalDate dateTime;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
