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
public class ListIssuesResponse {
    private boolean status;
    private List<DeveloperIssue> developerIssues;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperIssue {
        private String firstName;
        private String lastName;
        private String gitHubUserName;
        private Integer noOfIssues;
        private List<ListIssuesResponse.DeveloperIssue.Issue> issues;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Issue {
            private String url;
            private String state;
        }
    }
}
