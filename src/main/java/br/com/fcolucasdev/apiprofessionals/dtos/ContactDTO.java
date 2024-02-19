package br.com.fcolucasdev.apiprofessionals.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fcolucasdev.apiprofessionals.entities.Contact;
import lombok.Data;

@Data
public class ContactDTO {
  private UUID id;
  private String name;
  private String contact;
  private LocalDateTime createdAt;
  private UUID professionalId;

  public ContactDTO() {
  }

  public ContactDTO(UUID id, String name, String contact, LocalDateTime createdAt, UUID professionalId) {
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.createdAt = createdAt;
    this.professionalId = professionalId;
  }

  public ContactDTO(Contact contact) {
    this.id = contact.getId();
    this.name = contact.getName();
    this.contact = contact.getContact();
    this.createdAt = contact.getCreatedAt();
    this.professionalId = contact.getProfessional().getId();
  }
}
