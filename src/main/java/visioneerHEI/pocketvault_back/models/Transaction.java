package visioneerHEI.pocketvault_back.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column private Double amount;

  @Column private String label;

  @Column @GeneratedValue private Timestamp date;

  @Column private String type;

  @ManyToOne
  @JoinColumn(name = "id_kid")
  private User user;
}
