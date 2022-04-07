package com.topinternacional.linx.model.admin.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.admin.UnidadeConfig;

@Repository
public interface UnidadesConfigRepository extends CrudRepository<UnidadeConfig, Long> {

	Page<UnidadeConfig> findByCodMicrovixOrCodPci(Integer codMicrovix, Integer codPci, Pageable pageable);
	UnidadeConfig findByCodMicrovixOrCodPci(Integer codMicrovix, Integer codPci);
	UnidadeConfig findByCodUnidade(Integer id);
	UnidadeConfig findByCnpj(String cnpj);
	
	@Query( value = "SELECT e.* FROM TOPT_LINX_REL_UNID e ORDER BY e.cod_unidade"
			  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_LOG e ORDER BY e.cod_unidade"
	          , nativeQuery = true)
	Page<UnidadeConfig> findAllOrderByCodUnidade(Pageable pageable);
}