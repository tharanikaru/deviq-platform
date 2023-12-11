package com.tharani.deviqplatform.service;

import com.tharani.deviqplatform.client.GitHubIngestorServiceClient;
import com.tharani.deviqplatform.client.UserServiceClient;
import com.tharani.deviqplatform.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final UserServiceClient userServiceClient;
    private final GitHubIngestorServiceClient gitHubIngestorServiceClient;

    public SignInResponse signIn(SignInRequest request) {
        return userServiceClient.signIn(request);
    }

    public SignUpResponse signUp(SignUpRequest request) {
        return userServiceClient.signUp(request);
    }

    public TaskResponse updateData() {
        String message = "";
        boolean status = true;
        if (!gitHubIngestorServiceClient.updateCommits()) {
            status = false;
            message = "Failed to update commits";
        }

        if (!gitHubIngestorServiceClient.updateIssues()) {
            status = false;
            message += "|Failed to update issues";
        }

        if (!gitHubIngestorServiceClient.updatePullRequests()) {
            status = false;
            message += "|Failed to update pull requests";
        }

        if (!gitHubIngestorServiceClient.updatePullRequestReviews()) {
            status = false;
            message += "|Failed to update pull request reviews";
        }
        return TaskResponse.builder()
                .status(status)
                .message(message)
                .build();
    }

    public AddDeveloperResponse addDeveloper(AddDeveloperRequest request) {
        return gitHubIngestorServiceClient.addDeveloper(request);
    }

    public ListDeveloperResponse listDevelopers() {
        return gitHubIngestorServiceClient.listDevelopers();
    }

    public ListCommitResponse listCommits() {
        return gitHubIngestorServiceClient.listCommits();
    }

    public ListIssuesResponse listIssues() {
        return gitHubIngestorServiceClient.listIssues();
    }

    public ListPullRequestResponse listPullRequests() {
        return gitHubIngestorServiceClient.listPullRequests();
    }

    public ListPRReviewResponse listPullRequestReviewResponse() {
        return gitHubIngestorServiceClient.listPullRequestReviews();
    }
}
