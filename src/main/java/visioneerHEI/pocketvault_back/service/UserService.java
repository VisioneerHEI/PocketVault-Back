package visioneerHEI.pocketvault_back.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import visioneerHEI.pocketvault_back.models.User;
import visioneerHEI.pocketvault_back.repository.UserRepository;

@Service
public class UserService {
  public final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findByEmail(String name) {
    return userRepository.findByEmail(name);
  }

  public Optional<User> findUserById(UUID id) {
    return userRepository.findById(id);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public Page<User> findAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public Optional<User> deleteUser(UUID id) {
    Optional<User> toFind = userRepository.findById(id);

    if (toFind.isPresent()) {
      userRepository.deleteById(id);
    }
    return toFind;
  }
}
