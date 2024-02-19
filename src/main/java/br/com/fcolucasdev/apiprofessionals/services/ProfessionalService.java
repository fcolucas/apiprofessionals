package br.com.fcolucasdev.apiprofessionals.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fcolucasdev.apiprofessionals.dtos.ProfessionalDTO;
import br.com.fcolucasdev.apiprofessionals.entities.Professional;
import br.com.fcolucasdev.apiprofessionals.repositories.IProfessionalRepository;
import br.com.fcolucasdev.apiprofessionals.utils.Utils;

@Service
public class ProfessionalService {

  @Autowired
  private IProfessionalRepository professionalRepository;

  @Transactional
  public List<Object> findAll(String q, List<String> fields) {
    var professionalList = professionalRepository
        .findByNameContainingIgnoreCaseOrPositionContainingIgnoreCase(q, q);

    var professionalsDto = professionalList.stream().map(ProfessionalDTO::new).collect(Collectors.toList());
    // return a list of professionals with only the fields informed
    return professionalsDto.stream().map(professional -> Utils.filterFields(professional, fields))
        .collect(Collectors.toList());
  }

  @Transactional
  public ProfessionalDTO create(ProfessionalDTO professionalDto) {
    var professional = new Professional();
    professional.setName(professionalDto.getName());
    professional.setPosition(professionalDto.getPosition());
    professional.setBornDate(professionalDto.getBornDate());

    var professionalSaved = professionalRepository.save(professional);
    return new ProfessionalDTO(professionalSaved);
  }

  @Transactional
  public ProfessionalDTO findById(UUID id) throws Exception {
    var professional = professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      throw new Exception("Profissional não encontrado");
    }

    return new ProfessionalDTO(professional);
  }

  @Transactional
  public ProfessionalDTO update(UUID id, ProfessionalDTO professionalDTO) throws Exception {
    var professional = professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      throw new Exception("Profissional não encontrado");
    }

    Utils.copyNonNullProperties(professionalDTO, professional);

    var professionalSaved = professionalRepository.save(professional);
    return new ProfessionalDTO(professionalSaved);
  }

  @Transactional
  public void delete(UUID id) throws Exception {
    var professional = professionalRepository.findById(id).orElse(null);
    if (professional == null) {
      throw new Exception("Profissional não encontrado");
    }

    professionalRepository.delete(professional);
  }
}
