package visioneerHEI.pocketvault_back.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import visioneerHEI.pocketvault_back.models.ExchangeRateResponse;

@Service
public class ExchangeRateService {
    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ExchangeRateResponse getExchangeRate(String currencyCode) {
        String url = "https://v6.exchangerate-api.com/v6/3ae58fe2fbdd8bd77bb08470/latest/" + currencyCode;
        return restTemplate.getForObject(url, ExchangeRateResponse.class);
    }

    public ExchangeRateResponse getExchangeRate() {
        // Call the other method with "USD" as the default currency code
        return getExchangeRate("USD");
    }
}
