package pl.postek.my_repo_list.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.postek.my_repo_list.domain.Branch;
import pl.postek.my_repo_list.domain.ListOfRepo;

import java.util.Map;

@Repository
@Slf4j
public class MyRepoListRepository {

    private static final String URL = "https://api.github.com/users/{user}/repos";
    private static final String BRANCH_URL = "https://api.github.com/repos/{owner}/{repo}/branches";

    private final RestTemplate restTemplate;
    private final String repositories;
    private final String desiredUser;
    private final String desiredRepo;


    public MyRepoListRepository(final RestTemplate restTemplate,
                                @Value("${user.repo.name}") final String repositories,
                                @Value("${desired.repo}") final String desiredRepo,
                                @Value("${desired.user}") final String desiredUser) {
        this.restTemplate = restTemplate;
        this.repositories = repositories;
        log.info("user.name [{}]", repositories);

        this.desiredUser = desiredUser;
        log.info("desired.user [{}]", desiredUser);

        this.desiredRepo = desiredRepo;
        log.info("desired.repo [{}]", desiredRepo);
    }

    public String readRawJson() {
        return restTemplate.getForObject(URL, String.class);
    }

    public ListOfRepo[] readRepository() {
        Map<String, String> param = Map.of(
                "user", "AnnaPostek"
        );
        ResponseEntity<ListOfRepo[]> response = restTemplate.getForEntity(URL, ListOfRepo[].class, param);
        log.info("response:[{}]", response);
        log.info("response status :[{}]", response.getStatusCode());
        response.getHeaders()
                .forEach((key, value) -> log.info("header key: [{}] - value [{}]", key, value));
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        return null;
    }
    //some changes
    public Branch[] listBranches() {
        Map<String, String> param = Map.of(
                "owner", desiredUser,
                "repo", desiredRepo
        );
        Branch[] result = new Branch[0];
        ResponseEntity<Branch[]> responseBranches = restTemplate.getForEntity(BRANCH_URL, Branch[].class, param);

        if (responseBranches.getStatusCode().is2xxSuccessful()) {
            return responseBranches.getBody();
        }
        return result;
    }

}
