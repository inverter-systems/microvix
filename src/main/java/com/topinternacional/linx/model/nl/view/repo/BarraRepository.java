package com.topinternacional.linx.model.nl.view.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.nl.view.Barra;

@Repository
public interface BarraRepository extends CrudRepository<Barra, Long> {

	@Query( value = "SELECT e.* FROM TOPV_LINX_BARRAS e WHERE e.cod_produto = ?1 or e.setor = ?2"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_BARRAS e WHERE e.cod_produto = ?1 or e.setor = ?2"
		      , nativeQuery = true)
	Page<Barra> findByFilters(Integer codigo,  String setor,  Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_BARRAS e WHERE e.cod_produto = ?1"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_BARRAS e WHERE e.cod_produto = ?1"
		      , nativeQuery = true)
	Page<Barra> findByFilters(Integer codigo, Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_BARRAS e"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_BARRAS e"
		      , nativeQuery = true)
	Page<Barra> findByFilters(Pageable pageable);
} 