package visioneerHEI.pocketvault_back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import visioneerHEI.pocketvault_back.models.ExchangeRateResponse;
import visioneerHEI.pocketvault_back.service.ExchangeRateService;

@RestController
@CrossOrigin
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/exchange-rate/{currencyCode}")
    public ExchangeRateResponse getExchangeRate(@PathVariable String currencyCode) {
        return exchangeRateService.getExchangeRate(currencyCode);
    }

    @GetMapping("/exchange-rate")
    public ExchangeRateResponse getExchangeRate() {
        // This will call the getExchangeRate() method with "USD" as the default
        return exchangeRateService.getExchangeRate();
    }
}

