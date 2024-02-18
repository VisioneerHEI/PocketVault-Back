package visioneerHEI.pocketvault_back.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import visioneerHEI.pocketvault_back.models.CryptoBalance;
import visioneerHEI.pocketvault_back.repository.CryptoBalanceRepository;

@Service
public class CryptoBalanceService {
  public CryptoBalanceService(CryptoBalanceRepository cryptoBalanceRepository) {
    this.cryptoBalanceRepository = cryptoBalanceRepository;
  }

  public final CryptoBalanceRepository cryptoBalanceRepository;

  public Optional<CryptoBalance> findByid(UUID id) {
    return cryptoBalanceRepository.findById(id);
  }

  public CryptoBalance save(CryptoBalance cryptoBalance) {
    return cryptoBalanceRepository.save(cryptoBalance);
  }

  public Optional<CryptoBalance> delete(UUID id) {
    Optional<CryptoBalance> toFind = cryptoBalanceRepository.findById(id);
    if (toFind.isPresent()) {
      cryptoBalanceRepository.deleteById(id);
    }
    return toFind;
  }

  public Double getLastCryptoBalance(UUID id) {
    List<CryptoBalance> cryptoBalance =
        cryptoBalanceRepository.findAll(Sort.sort(Date.class).descending());

    if (!cryptoBalance.isEmpty()) {
      return cryptoBalance.get(0).getBalance();
    }

    return 0D;
  }
}
