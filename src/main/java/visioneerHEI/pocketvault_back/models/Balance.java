package visioneerHEI.pocketvault_back.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column private Double amount;

  @Column @GeneratedValue private Timestamp date;

  @ManyToOne
  @JoinColumn(name = "id_kid")
  private User user;
}
