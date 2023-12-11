package com.tharani.deviqplatform.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddDeveloperRequest {
    private String firstName;
    private String lastName;
    private String gitHubUserName;
}
