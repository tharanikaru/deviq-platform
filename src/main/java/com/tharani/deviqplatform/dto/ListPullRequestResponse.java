package com.tharani.deviqplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPullRequestResponse {
    private boolean status;
    private List<ListPullRequestResponse.DeveloperPullRequest> developerPullRequests;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperPullRequest {
        private String firstName;
        private String lastName;
        private String gitHubUserName;
        private Integer noOfPullRequests;
        private List<ListPullRequestResponse.DeveloperPullRequest.PullRequest> pullRequests;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class PullRequest {
            private String url;
            private String state;
            private String title;
        }
    }
}
