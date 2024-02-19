package br.com.fcolucasdev.apiprofessionals.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fcolucasdev.apiprofessionals.entities.Contact;

public interface IContactRepository extends JpaRepository<Contact, UUID> {
  List<Contact> findByNameContainingIgnoreCaseOrContactContainingIgnoreCase(String name, String contact);

}
