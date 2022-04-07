package com.topinternacional.linx.model.nl.ai.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.ai.AiCrTitulos;

@Repository
public interface AiCrTitulosRepository extends CrudRepository<AiCrTitulos, Long> {

	@Query( value = "SELECT * FROM AI_CR_TITULOS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2"
		      , countQuery = "SELECT COUNT(*) FROM AI_CR_TITULOS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2"
		      , nativeQuery = true)
	List<AiCrTitulos> findByCodUnidadeAndNumDocumento(Integer codUnidade, Integer numDocumento);
	
}