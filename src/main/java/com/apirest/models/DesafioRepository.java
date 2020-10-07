package com.apirest.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DesafioRepository extends JpaRepository<Foto, Long>{

	Optional<Foto> findById(Long id);
	
	
}
