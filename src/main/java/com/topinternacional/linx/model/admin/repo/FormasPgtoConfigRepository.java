package com.topinternacional.linx.model.admin.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.admin.FormasPgtoConfig;

@Repository
public interface FormasPgtoConfigRepository extends CrudRepository<FormasPgtoConfig, Long> {

		
	@Query( value = "SELECT e.* FROM TOPT_LINX_COND_PGTO e WHERE e.sistema = 'PCI' AND e.codigo = ?1 ORDER BY codigo" 
			  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_COND_PGTO e WHERE e.sistema = 'PCI' AND e.codigo = ?1 ORDER BY codigo"
	          , nativeQuery = true)
	FormasPgtoConfig findCondPgtoPci(Integer codigo);
	
	@Query( value = "SELECT e.* FROM TOPT_LINX_COND_PGTO e WHERE e.sistema = 'MICROVIX' AND e.codigo = ?1 ORDER BY codigo" 
			  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_COND_PGTO e WHERE e.sistema = 'MICROVIX' AND e.codigo = ?1 ORDER BY codigo"
	          , nativeQuery = true)
	FormasPgtoConfig findCondPgtoMICROVIX(Integer codigo);
}