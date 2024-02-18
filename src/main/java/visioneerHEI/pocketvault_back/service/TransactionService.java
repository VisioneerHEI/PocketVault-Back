package visioneerHEI.pocketvault_back.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import visioneerHEI.pocketvault_back.models.Transaction;
import visioneerHEI.pocketvault_back.repository.TransactionRepository;

@Service
@RequiredArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepo;
  private final BalanceService balanceService;

  public Optional<Transaction> delete(UUID id) {
    Optional<Transaction> toDelete = transactionRepo.findById(id);
    if (toDelete.isPresent()) {
      transactionRepo.deleteById(id);
    }
    return toDelete;
  }

  public List<Transaction> findByKidId(UUID id) {
    return transactionRepo.findByUserId(id);
  }

  public Optional<Transaction> findById(UUID id) {
    return transactionRepo.findById(id);
  }

  public Transaction save(Transaction transaction) {
    return transactionRepo.save(transaction);
  }
}
