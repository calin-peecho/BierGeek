package com.example.demo.service;

import com.example.demo.entity.Bier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BierPunkService {

//    private final RestTemplate restTemplate;
//
//    public BierPunkService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }



    public Bier[] getRandom() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String fooResourceUrl = "https://api.punkapi.com/v2/beers/random";
        return restTemplate.getForObject(fooResourceUrl, Bier[].class);
//        return this.restTemplate.getForObject(url, Post[].class);
//        for(Bier re : response ){
//            System.out.println(re);
//        }
    }
}
