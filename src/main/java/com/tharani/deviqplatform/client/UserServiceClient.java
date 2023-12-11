package com.tharani.deviqplatform.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tharani.deviqplatform.dto.SignInRequest;
import com.tharani.deviqplatform.dto.SignInResponse;
import com.tharani.deviqplatform.dto.SignUpRequest;
import com.tharani.deviqplatform.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceClient {

    @Value("${user-service.address}")
    private String END_POINT;

    public SignInResponse signIn(SignInRequest signInRequest) {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/signin");
            HttpClient httpClient = HttpClient.newHttpClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(signInRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToSignInResponse(response);
        } catch (Exception e) {
            log.error("Error occurred while signin: ", e);
            throw new RuntimeException("Failed to sign-in");
        }
    }

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        try {
            URI uri = URI.create(END_POINT+ "/api/v1/signup");
            HttpClient httpClient = HttpClient.newHttpClient();
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(signUpRequest);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return mapToSignUpResponse(response);
        } catch (Exception e) {
            log.error("Error occurred while signup: ", e);
            throw new RuntimeException("Failed to sign-up");
        }
    }


    public SignInResponse mapToSignInResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), SignInResponse.class);
    }

    public SignUpResponse mapToSignUpResponse(HttpResponse<String> response) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(), SignUpResponse.class);
    }
}
