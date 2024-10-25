package mft.model.service;

import mft.model.entity.Branch;
import mft.model.respository.BranchRepository;

import java.util.List;

public class BranchService {
    public static void saveAll(List<Branch> branchList) throws Exception {
        BranchRepository branchRepo = new BranchRepository();
        branchRepo.saveAll(branchList);
    }

    public static List<Branch> loadAll() throws Exception {
        BranchRepository branchRepo = new BranchRepository();
        return branchRepo.loadAll();
    }
}
