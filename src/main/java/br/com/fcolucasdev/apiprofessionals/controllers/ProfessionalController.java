package br.com.fcolucasdev.apiprofessionals.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.fcolucasdev.apiprofessionals.dtos.ProfessionalDTO;
import br.com.fcolucasdev.apiprofessionals.services.ProfessionalService;

@RestController
@RequestMapping(value = "/profissionais", consumes = "application/json", produces = "application/json")
public class ProfessionalController {

  @Autowired
  private ProfessionalService professionalService;

  @GetMapping
  public ResponseEntity list(@RequestParam(required = false) String q,
      @RequestParam(required = false) List<String> fields) {
    try {
      var professionalList = professionalService.findAll(q, fields);
      return ResponseEntity.ok().body(professionalList);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity create(@RequestBody ProfessionalDTO professionalDto) {
    try {
      var professionalCreated = professionalService.create(professionalDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(professionalCreated);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable UUID id) {
    try {
      var professional = professionalService.findById(id);
      return ResponseEntity.ok().body(professional);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity put(@PathVariable UUID id, @RequestBody ProfessionalDTO professionalDTO) {
    try {
      var professionalUpdated = professionalService.update(id, professionalDTO);
      return ResponseEntity.ok().body(professionalUpdated);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable UUID id) {
    try {
      professionalService.delete(id);
      return ResponseEntity.ok().body("Profissional removido");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
