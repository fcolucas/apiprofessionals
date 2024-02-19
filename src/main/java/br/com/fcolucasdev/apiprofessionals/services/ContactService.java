package br.com.fcolucasdev.apiprofessionals.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fcolucasdev.apiprofessionals.dtos.ContactDTO;
import br.com.fcolucasdev.apiprofessionals.entities.Contact;
import br.com.fcolucasdev.apiprofessionals.repositories.IContactRepository;
import br.com.fcolucasdev.apiprofessionals.repositories.IProfessionalRepository;
import br.com.fcolucasdev.apiprofessionals.utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class ContactService {

  @Autowired
  private IContactRepository contactRepository;

  @Autowired
  private IProfessionalRepository professionalRepository;

  @Transactional
  public List<Object> findAll(String q, List<String> fields) {
    var contactList = contactRepository
        .findByNameContainingIgnoreCaseOrContactContainingIgnoreCase(q, q);

    var contactsDto = contactList.stream().map(ContactDTO::new).collect(Collectors.toList());
    // return a list of contacts with only the fields informed
    return contactsDto.stream().map(contact -> Utils.filterFields(contact, fields))
        .collect(Collectors.toList());
  }

  @Transactional
  public ContactDTO findById(UUID id) throws Exception {
    var contact = contactRepository.findById(id).orElse(null);
    if (contact == null) {
      throw new Exception("Contato não encontrado");
    }

    return new ContactDTO(contact);
  }

  @Transactional
  public ContactDTO create(ContactDTO contactDto) {
    var professional = professionalRepository.findById(contactDto.getProfessionalId()).orElse(null);
    if (professional == null) {
      throw new RuntimeException("Profissional não encontrado");
    }

    var contact = new Contact();
    contact.setName(contactDto.getName());
    contact.setContact(contactDto.getContact());
    contact.setProfessional(professional);

    var savedContact = contactRepository.save(contact);
    return new ContactDTO(savedContact);
  }

  @Transactional
  public ContactDTO update(UUID id, ContactDTO contactDto) throws Exception {
    var contact = contactRepository.findById(id).orElse(null);
    if (contact == null) {
      throw new Exception("Contato não encontrado");
    }

    if (contact.getProfessional().getId() != contactDto.getProfessionalId()) {
      var professional = professionalRepository.findById(contactDto.getProfessionalId()).orElse(null);
      if (professional == null) {
        throw new Exception("Profissional não encontrado");
      }
      contact.setProfessional(professional);
    }

    Utils.copyNonNullProperties(contactDto, contact);
    var savedContact = contactRepository.save(contact);
    return new ContactDTO(savedContact);
  }

  @Transactional
  public void delete(UUID id) throws Exception {
    var contact = contactRepository.findById(id).orElse(null);
    if (contact == null) {
      throw new Exception("Contato não encontrado");
    }

    contactRepository.delete(contact);
  }
}
