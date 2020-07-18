package pl.postek.my_repo_list.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @JsonProperty("name")
    private String branchName;
    @JsonProperty("protected")
    private boolean isProtected;
    private CommitInfo commitInfo;

}
