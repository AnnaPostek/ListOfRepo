package pl.postek.my_repo_list.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.postek.my_repo_list.domain.Branch;
import pl.postek.my_repo_list.domain.ListOfRepo;
import pl.postek.my_repo_list.service.MyRepoListService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1")
public class MyRepoListRestController {
    private MyRepoListService service;

    public MyRepoListRestController(MyRepoListService service) {
        this.service = service;
    }

    @GetMapping("/postek")
    public String repo(){
        log.info("my repo [{}]");
        return service.getList();
    }

    @GetMapping("/users/AnnaPostek/repos")
    public ListOfRepo[] getRepoList() {
        ListOfRepo[] repository = service.getRepository();
        log.info("repository:  [{}]", repository);
        return repository;

    }

    @GetMapping("/my-repo-branches")
    public ResponseEntity<List<Branch>> getMyBranches() {
        List<Branch> result = service.getBranches();
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
