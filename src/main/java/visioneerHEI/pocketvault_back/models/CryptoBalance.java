package visioneerHEI.pocketvault_back.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "balance")
public class CryptoBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column private Double balance;

  @Column @GeneratedValue private Timestamp date;

  @Column private String currency_code;
  @Column private String wallet_address;
  @Column private String private_key;
  @Column private String network;

  @ManyToOne
  @JoinColumn(name = "id_user")
  private User user;
}
