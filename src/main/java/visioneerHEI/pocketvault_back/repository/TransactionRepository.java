package visioneerHEI.pocketvault_back.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import visioneerHEI.pocketvault_back.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
  List<Transaction> findByUserId(UUID id);
}
