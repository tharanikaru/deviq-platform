package com.tharani.deviqplatform.controller;

import com.tharani.deviqplatform.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.tharani.deviqplatform.service.PlatformService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deviq")
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping("/signup")
    public SignUpResponse signup(@RequestBody SignUpRequest request) {
        return platformService.signUp(request);
    }

    @PostMapping("/signin")
    public SignInResponse signin(@RequestBody SignInRequest request) {
        return platformService.signIn(request);
    }

    @GetMapping("/update")
    public TaskResponse update() {
        return platformService.updateData();
    }

    @PostMapping("/developer/add")
    public AddDeveloperResponse addDeveloper(@RequestBody AddDeveloperRequest request) {
        return platformService.addDeveloper(request);
    }

    @GetMapping("developer/list")
    public ListDeveloperResponse listDevelopers() {
        return platformService.listDevelopers();
    }

    @GetMapping("developer/commits")
    public ListCommitResponse listCommits() {
        return platformService.listCommits();
    }

    @GetMapping("developer/issues")
    public ListIssuesResponse listIssues() {
        return platformService.listIssues();
    }

    @GetMapping("developer/pull-requests")
    public ListPullRequestResponse listPullRequestResponse() {
        return platformService.listPullRequests();
    }

    @GetMapping("developer/pull-request-reviews")
    public ListPRReviewResponse listPullRequestReviewResponse() {
        return platformService.listPullRequestReviewResponse();
    }


}
