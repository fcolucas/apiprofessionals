package br.com.fcolucasdev.apiprofessionals.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fcolucasdev.apiprofessionals.entities.Professional;
import lombok.Data;

@Data
public class ProfessionalDTO {
  private UUID id;
  private String name;
  private String position;
  private LocalDateTime bornDate;
  private LocalDateTime createdAt;
  private Set<ContactDTO> contacts = new HashSet<>();

  public ProfessionalDTO() {
  }

  public ProfessionalDTO(UUID id, String name, String position, LocalDateTime bornDate, LocalDateTime createdAt,
      Set<ContactDTO> contacts) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.bornDate = bornDate;
    this.createdAt = createdAt;
    this.contacts = contacts;
  }

  public ProfessionalDTO(Professional professional) {
    this.id = professional.getId();
    this.name = professional.getName();
    this.position = professional.getPosition();
    this.bornDate = professional.getBornDate();
    this.createdAt = professional.getCreatedAt();
    this.contacts = professional.getContacts().stream().map(ContactDTO::new).collect(Collectors.toSet());
  }
}
