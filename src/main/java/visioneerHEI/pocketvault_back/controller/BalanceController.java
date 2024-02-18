package visioneerHEI.pocketvault_back.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import visioneerHEI.pocketvault_back.service.BalanceService;

@RestController
@CrossOrigin
@RequestMapping("/balance")
public class BalanceController {
  public final BalanceService balanceService;

  public BalanceController(BalanceService balanceService) {
    this.balanceService = balanceService;
  }

  @GetMapping
  public Double getLastAmountOnBalance(UUID id) {
    return balanceService.getLastBalance(id);
  }
}
