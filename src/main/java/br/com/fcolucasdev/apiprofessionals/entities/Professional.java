package br.com.fcolucasdev.apiprofessionals.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "tb_professional")
public class Professional {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;
  private String position;
  private LocalDateTime bornDate;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Contact> contacts = new HashSet<>();
}
