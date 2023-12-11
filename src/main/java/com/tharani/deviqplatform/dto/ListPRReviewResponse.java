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
public class ListPRReviewResponse {
    private boolean status;
    private List<ListPRReviewResponse.DeveloperPullRequestReview> developerPullRequestReviews;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeveloperPullRequestReview {
        private String firstName;
        private String lastName;
        private String gitHubUserName;
        private Integer noOfReviews;
        private List<ListPRReviewResponse.DeveloperPullRequestReview.Review> reviews;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Review {
            private String url;
            private String state;
        }
    }
}
