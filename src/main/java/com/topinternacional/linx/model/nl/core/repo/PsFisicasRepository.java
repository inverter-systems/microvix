package com.topinternacional.linx.model.nl.core.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.nl.core.PsFisicas;

@Repository
public interface PsFisicasRepository extends CrudRepository<PsFisicas, Long> {

	@Query( value = "SELECT COD_PESSOA FROM PS_FISICAS WHERE NUM_CPF = ?1 AND ROWNUM  = 1"
		      , countQuery = "SELECT COUNT(*) FROM PS_FISICAS WHERE NUM_CPF = ?1 AND ROWNUM  = 1"
		      , nativeQuery = true)
	Long findByNumCpf(Long numCgc);

	@Query( value = "SELECT COD_PESSOA FROM PS_FISICAS WHERE NUM_CPF = ?1 AND ROWNUM  = 1"
		      , countQuery = "SELECT COUNT(*) FROM PS_FISICAS WHERE NUM_CPF = ?1 AND ROWNUM  = 1"
		      , nativeQuery = true)
	Long findByNumCpf(String cpf);
	
}