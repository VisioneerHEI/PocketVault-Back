package visioneerHEI.pocketvault_back.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import visioneerHEI.pocketvault_back.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  public User findByEmail(String email);
}
