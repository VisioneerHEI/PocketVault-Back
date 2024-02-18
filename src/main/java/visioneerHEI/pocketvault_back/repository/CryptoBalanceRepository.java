package visioneerHEI.pocketvault_back.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import visioneerHEI.pocketvault_back.models.CryptoBalance;

@Repository
public interface CryptoBalanceRepository extends JpaRepository<CryptoBalance, UUID> {}
