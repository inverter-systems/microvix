package com.topinternacional.linx.model.nl.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.core.PsJuridicas;

@Repository
public interface PsJuridicasRepository extends CrudRepository<PsJuridicas, Long> {

	@Query( value = "SELECT COD_PESSOA FROM PS_JURIDICAS WHERE NUM_CGC = ?1 AND ROWNUM  = 1"
		      , countQuery = "SELECT COUNT(*) FROM PS_JURIDICAS WHERE NUM_CGC = ?1 AND ROWNUM  = 1"
		      , nativeQuery = true)
	Long findByNumCgc(Long numCgc);

}