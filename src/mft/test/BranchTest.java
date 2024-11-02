package mft.test;

import mft.model.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchTest {
    public static void main(String[] args) throws Exception {
        Branch branch = Branch
                .builder()
                .id(1)
                .title("TehranShop")
                .address("Tehran")
                .phone("0214456245")
                .area("Tehran")
                .active(true)
                .build();


        String line = "2,IsfahanShop,Isfahan,03244679873,Isfahan,false";
        Branch branchByCsv = new Branch(line);

        System.out.println(branch);
        System.out.println(branchByCsv);

        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);
        branchList.add(branchByCsv);

        System.out.println(branch);
        System.out.println(branch.toCsv());

    }
}
