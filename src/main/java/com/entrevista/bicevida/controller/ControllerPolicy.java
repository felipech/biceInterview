package com.entrevista.bicevida.controller;

import com.entrevista.bicevida.dto.PolicyData;
import com.entrevista.bicevida.dto.ResultPolicy;
import com.entrevista.bicevida.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/policy/calculos")
public class ControllerPolicy {

    private WebClient webClient;

    private final String urlBase = "https://dn8mlk7hdujby.cloudfront.net/interview/insurance/policy";

    @Autowired
    private PolicyService policyService;




    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl(urlBase)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @GetMapping("/test")
    public String pruebaController(){
        return "Test controller";
    }

    @GetMapping("/")
    public Mono<ResultPolicy> calculoPolicy(){

        Mono<PolicyData> policyData = webClient.get().uri("/").retrieve().bodyToMono(PolicyData.class);

        return policyService.convertirData(policyData);
    }
}
