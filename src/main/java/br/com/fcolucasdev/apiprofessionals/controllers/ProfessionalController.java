package br.com.fcolucasdev.apiprofessionals.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fcolucasdev.apiprofessionals.entities.Professional;
import br.com.fcolucasdev.apiprofessionals.repositories.IProfessionalRepository;
import br.com.fcolucasdev.apiprofessionals.utils.Utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/profissionais")
public class ProfessionalController {

  @Autowired
  private IProfessionalRepository professionalRepository;

  @GetMapping
  public ResponseEntity list() {
    var professionalList = this.professionalRepository.findAll();
    return ResponseEntity.ok().body(professionalList);
  }

  @PostMapping
  public ResponseEntity create(@RequestBody Professional professional) {
    var professionalCreated = this.professionalRepository.save(professional);
    return ResponseEntity.status(HttpStatus.CREATED).body(professionalCreated);
  }

  @GetMapping("/{id}")
  public ResponseEntity get(@PathVariable UUID id) {
    var professional = this.professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      return ResponseEntity.badRequest().body("Profissional não encontrado");
    }
    return ResponseEntity.ok().body(professional);
  }

  @PutMapping("/{id}")
  public ResponseEntity put(@PathVariable UUID id, @RequestBody Professional editedProfessional) {
    var professional = this.professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      return ResponseEntity.badRequest().body("Profissional não encontrado");
    }

    Utils.copyNonNullProperties(editedProfessional, professional);
    var professionalUpdated = this.professionalRepository.save(professional);
    return ResponseEntity.ok().body(professionalUpdated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable UUID id) {
    var professional = this.professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      return ResponseEntity.badRequest().body("Profissional não encontrado");
    }
    this.professionalRepository.delete(professional);
    return ResponseEntity.ok().body("Profissional removido");
  }

}
