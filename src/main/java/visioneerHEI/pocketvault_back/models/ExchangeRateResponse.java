package visioneerHEI.pocketvault_back.models;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponse {
  private String result;
  private String base_code;
  private Map<String, Double> conversion_rates;
}
