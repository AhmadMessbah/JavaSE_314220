package mft.model.entity;

import lombok.*;
import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Branch {
    private int id;
    private String title;
    private String address;
    private String phone;
    private String area;
    private boolean active;

    public Branch(String data) {
        String[] branchData = data.split(",");
        this.id = Integer.parseInt(branchData[0]);
        this.title = branchData[1];
        this.address = branchData[2];
        this.phone = branchData[3];
        this.area = branchData[4];
        this.active = Boolean.parseBoolean(branchData[5]);
    }

    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s,%s", id, title, address, phone, area, active);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}