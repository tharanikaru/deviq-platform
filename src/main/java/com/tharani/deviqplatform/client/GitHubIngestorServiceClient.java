package com.tharani.deviqplatform.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tharani.deviqplatform.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "[GitHubIngestorServiceClient]")
public class GitHubIngestorServiceClient {

    @Value("${github-ingestor-service.address}")
    private String END_POINT;

    public boolean updateCommits() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/git/commits");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToTaskResponse(response).isStatus();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update commits");
        }
    }

    public boolean updateIssues() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/git/issues");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToTaskResponse(response).isStatus();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update issues");
        }
    }

    public boolean updatePullRequests() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/git/pull-requests");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToTaskResponse(response).isStatus();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update pull-requests");
        }
    }

    public boolean updatePullRequestReviews() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/git/pull-request-reviews");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToTaskResponse(response).isStatus();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update pull-request reviews");
        }
    }

    public AddDeveloperResponse addDeveloper(AddDeveloperRequest addDeveloperRequest) {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/addDeveloper");
            HttpClient httpClient = HttpClient.newHttpClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(addDeveloperRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToAddDeveloperResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add developer", e);
        }
    }

    public ListDeveloperResponse listDevelopers() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/listDevelopers");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToListDeveloperResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to list developer", e);
        }
    }

    public ListCommitResponse listCommits() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/developer-commits");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToListCommitResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to list commits", e);
        }
    }

    public ListIssuesResponse listIssues() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/developer-issues");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToListIssuesResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to list issues", e);
        }
    }

    public ListPullRequestResponse listPullRequests() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/developer-pull-requests");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToListPullRequestsResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to list pull requests", e);
        }
    }

    public ListPRReviewResponse listPullRequestReviews() {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/metric/developer-pull-request-reviews");
            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToListPullRequestsReviewResponse(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to list pr reviews", e);
        }
    }

    private TaskResponse mapToTaskResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), TaskResponse.class);
    }

    private AddDeveloperResponse mapToAddDeveloperResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), AddDeveloperResponse.class);
    }

    private ListCommitResponse mapToListCommitResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), ListCommitResponse.class);
    }

    private ListDeveloperResponse mapToListDeveloperResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), ListDeveloperResponse.class);
    }

    private ListIssuesResponse mapToListIssuesResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), ListIssuesResponse.class);
    }
    private ListPullRequestResponse mapToListPullRequestsResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), ListPullRequestResponse.class);
    }

    private ListPRReviewResponse mapToListPullRequestsReviewResponse(HttpResponse<String> response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), ListPRReviewResponse.class);
    }



}
