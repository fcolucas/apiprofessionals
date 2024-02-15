package br.com.fcolucasdev.apiprofessionals.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fcolucasdev.apiprofessionals.entities.Professional;

public interface IProfessionalRepository extends JpaRepository<Professional, UUID> {

}
