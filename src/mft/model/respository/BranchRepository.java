package mft.model.respository;

import mft.model.entity.Branch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BranchRepository {
    public void saveAll(List<Branch> branchList) throws IOException {
        File fs = new File("branch.csv");
        fs.delete();
        fs.createNewFile();
        FileWriter fsWriter = new FileWriter(fs);
        fsWriter.write("id,title,address,phone,area,active\n");
        for (Branch branch : branchList) {
            fsWriter.write(branch.toCsv() + "\n");
        }
        fsWriter.close();
    }

    public List<Branch> loadAll() throws IOException {
        File fs = new File("branch.csv");
        Scanner sc = new Scanner(fs);
        List<Branch> branchList = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Branch branch = new Branch(line);
            branchList.add(branch);
        }
        return branchList;
    }
}
