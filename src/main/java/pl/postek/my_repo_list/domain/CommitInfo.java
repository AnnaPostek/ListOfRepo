package pl.postek.my_repo_list.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitInfo {

    private String sha;
    private String url;
}
