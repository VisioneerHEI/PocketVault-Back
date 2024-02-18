package visioneerHEI.pocketvault_back.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import visioneerHEI.pocketvault_back.models.Balance;
import visioneerHEI.pocketvault_back.repository.BalanceRepository;

@Service
public class BalanceService {
  public BalanceService(BalanceRepository balanceRepository) {
    this.balanceRepository = balanceRepository;
  }

  public final BalanceRepository balanceRepository;

  public Optional<Balance> findByid(UUID id) {
    Optional<Balance> toFind = balanceRepository.findById(id);
    return toFind;
  }

  public Balance save(Balance balance) {
    Balance toSave = balanceRepository.save(balance);
    return toSave;
  }

  public Optional<Balance> delete(UUID id) {
    Optional<Balance> toFind = balanceRepository.findById(id);
    if (toFind.isPresent()) {
      balanceRepository.deleteById(id);
    }
    return toFind;
  }

  public Double getLastBalance(UUID id) {
    List<Balance> balance = balanceRepository.findAll(Sort.sort(Date.class).descending());

    if (!balance.isEmpty()) {
      return balance.get(0).getBalance();
    }

    return 0D;
  }
}
