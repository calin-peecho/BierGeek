package com.example.demo.service.impl;

import com.example.demo.entity.Bier;
import com.example.demo.repository.BierRepository;
import com.example.demo.service.BierService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class BierServiceImpl implements BierService {

    private final static String API_URL = "https://api.punkapi.com/v2/";

    //    private final RestTemplate restTemplate;
//
//    public BierServiceImpl(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }

    public Bier getRandom() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String fooResourceUrl = API_URL + "/beers/random";
        return Objects.requireNonNull(restTemplate.getForObject(fooResourceUrl, Bier[].class))[0];
    }
}
