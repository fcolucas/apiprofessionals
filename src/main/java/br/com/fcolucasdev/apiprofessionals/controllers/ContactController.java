package br.com.fcolucasdev.apiprofessionals.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fcolucasdev.apiprofessionals.dtos.ContactDTO;
import br.com.fcolucasdev.apiprofessionals.services.ContactService;

@RestController
@RequestMapping(value = "/contatos", consumes = "application/json", produces = "application/json")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @GetMapping
  public ResponseEntity list(@RequestParam(required = false) String q,
      @RequestParam(required = false) List<String> fields) {
    try {
      var contactList = contactService.findAll(q, fields);
      return ResponseEntity.ok(contactList);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable UUID id) {
    try {
      var contact = contactService.findById(id);
      return ResponseEntity.ok(contact);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity create(@RequestBody ContactDTO contactDto) {
    try {
      var contactCreated = contactService.create(contactDto);
      return ResponseEntity.ok(contactCreated);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable UUID id, @RequestBody ContactDTO contactDTO) {
    try {
      var updatedContact = contactService.update(id, contactDTO);
      return ResponseEntity.ok(updatedContact);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable UUID id) {
    try {
      contactService.delete(id);
      return ResponseEntity.ok().body("Contato removido");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
