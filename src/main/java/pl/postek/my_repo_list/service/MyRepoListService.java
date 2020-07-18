package pl.postek.my_repo_list.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.postek.my_repo_list.domain.Branch;
import pl.postek.my_repo_list.domain.ListOfRepo;
import pl.postek.my_repo_list.repository.MyRepoListRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class MyRepoListService {

    private MyRepoListRepository repository;

    public MyRepoListService(MyRepoListRepository repository) {
        this.repository = repository;
    }

    public String getList() {
        return repository.readRawJson();
    }

    public ListOfRepo[] getRepository() {
        ListOfRepo[] repos = repository.readRepository();
        log.info("repositories: [{}]", repos);
        return repos;
    }

    public List<Branch> getBranches() {
        List<Branch> result = Collections.emptyList();
        Branch[] branches = repository.listBranches();
        result = Arrays.stream(branches).collect(Collectors.toList());
//                forEach(branch -> result.add(branch));
        log.info("getBranches() received response: {}", result);
        return result;

    }
}
