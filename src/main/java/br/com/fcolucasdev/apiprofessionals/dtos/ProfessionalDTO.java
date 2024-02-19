package br.com.fcolucasdev.apiprofessionals.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.fcolucasdev.apiprofessionals.entities.Professional;
import lombok.Data;

@Data
public class ProfessionalDTO {
  private UUID id;
  private String name;
  private String position;
  private LocalDateTime bornDate;
  private LocalDateTime createdAt;
  private List<ContactDTO> contacts;

  public ProfessionalDTO() {
  }

  public ProfessionalDTO(UUID id, String name, String position, LocalDateTime bornDate, LocalDateTime createdAt,
      List<ContactDTO> contacts) {
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
    this.contacts = professional.getContacts().stream().map(ContactDTO::new).toList();
  }
}
