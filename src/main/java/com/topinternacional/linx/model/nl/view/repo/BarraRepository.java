package com.topinternacional.linx.model.nl.view.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.view.Barra;

@Repository
public interface BarraRepository extends CrudRepository<Barra, Long> {

	Page<Barra> findAll(Pageable pageable);
	
}