package com.topinternacional.linx.model.admin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.admin.OperacoesConfig;

@Repository
public interface OperacoesConfigRepository extends CrudRepository<OperacoesConfig, Integer> {
	
	OperacoesConfig findByCfop(Integer cfop);

	
}