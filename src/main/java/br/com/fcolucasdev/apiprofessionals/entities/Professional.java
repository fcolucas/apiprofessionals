package br.com.fcolucasdev.apiprofessionals.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
