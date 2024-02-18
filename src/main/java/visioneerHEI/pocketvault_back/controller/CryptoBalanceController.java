package visioneerHEI.pocketvault_back.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import visioneerHEI.pocketvault_back.service.CryptoBalanceService;

@RestController
@CrossOrigin
@RequestMapping("/cryptoBalance")
public class CryptoBalanceController {
  public final CryptoBalanceService cryptoBalanceService;

  public CryptoBalanceController(CryptoBalanceService cryptoBalanceService) {
    this.cryptoBalanceService = cryptoBalanceService;
  }

  @GetMapping
  public Double getLastAmountOnBalance(UUID id) {
    return cryptoBalanceService.getLastCryptoBalance(id);
  }
}
