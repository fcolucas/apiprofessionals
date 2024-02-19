package br.com.fcolucasdev.apiprofessionals.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "tb_contact")
public class Contact {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String name;
  private String contact;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "professional_id")
  private Professional professional;
}
