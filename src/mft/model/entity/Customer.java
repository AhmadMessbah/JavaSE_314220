package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Customer {
    private int id;
    private String name;
    private String familyName;
    private String username;
    private String password;
    private String phone;
    private boolean active;

    public Customer(String data) {
        String[] customerData = data.split(",");
        id = Integer.parseInt(customerData[0]);
        name = customerData[1];
        familyName = customerData[2];
        username = customerData[3];
        password = customerData[4];
        phone = customerData[5];
        active = Boolean.parseBoolean(customerData[6]);
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", id, name, familyName, username, password, phone, active);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
