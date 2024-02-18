package visioneerHEI.pocketvault_back.models;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tutor")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  UUID id;

  @Basic
  @Column(name = "first_name")
  String firstName;

  @Basic
  @Column(name = "last_name")
  String lastName;

  @Basic
  @Column(name = "email")
  String email;

  @Basic
  @Column(name = "password")
  String password;
}
