package br.com.fcolucasdev.apiprofessionals.dtos;

import java.util.UUID;

import br.com.fcolucasdev.apiprofessionals.entities.Contact;
import lombok.Data;

@Data
public class ContactDTO {
  private UUID id;
  private String name;
  private String contact;
  private UUID professionalId;

  public ContactDTO() {
  }

  public ContactDTO(UUID id, String name, String contact, UUID professionalId) {
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.professionalId = professionalId;
  }

  public ContactDTO(Contact contact) {
    this.id = contact.getId();
    this.name = contact.getName();
    this.contact = contact.getContact();
    this.professionalId = contact.getProfessional().getId();
  }
}
