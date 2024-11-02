package mft.model.service;

import mft.model.entity.Branch;
import mft.model.respository.BranchRepository;

import java.util.List;

public class BranchService {
    public static void create(Branch branch) throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            repo.save(branch);
        }
    }

    public static void update(Branch branch) throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            repo.edit(branch);
        }
    }

    public static void delete(int id) throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            repo.remove(id);
        }
    }

    public static List<Branch> getAll() throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            return repo.getAll();
        }
    }

    public static Branch getById(int id) throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            return repo.getByID(id);
        }
    }

    public static List<Branch> getByTitle(String branchName) throws Exception {
        try (BranchRepository repo = new BranchRepository()) {
            return repo.getByTitle();
        }
    }

}
