package com.topinternacional.linx.model.nl.core.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.nl.core.PsTelefones;

@Repository
public interface PsTelefonesRepository extends CrudRepository<PsTelefones, Long> {
	
	
}