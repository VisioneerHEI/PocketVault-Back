package visioneerHEI.pocketvault_back.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import visioneerHEI.pocketvault_back.models.Transaction;
import visioneerHEI.pocketvault_back.service.TransactionService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {
  private final TransactionService transactionService;

  @DeleteMapping("/{id}")
  public ResponseEntity<Transaction> getByID(@PathVariable UUID id) {
    return transactionService
        .delete(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{userId}")
  public List<Transaction> getTransaction(@PathVariable UUID userId) {
    return transactionService.findByKidId(userId);
  }

  @PutMapping
  public Transaction addTransaction(Transaction transaction) {
    return transactionService.save(transaction);
  }
}
