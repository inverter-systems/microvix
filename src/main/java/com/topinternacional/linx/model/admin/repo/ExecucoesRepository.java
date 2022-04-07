package com.topinternacional.linx.model.admin.repo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.admin.Execucoes;

@Repository
public interface ExecucoesRepository extends CrudRepository<Execucoes, Long> {

	Page<Execucoes> findAll(Pageable pageable);
	Page<Execucoes> findAllByOrderByIdDesc(Pageable pageable);
	
	@Query( value = "SELECT * FROM TOPT_LINX_EXEC WHERE DTA_INICIO BETWEEN ?1 AND ?2 ORDER BY ID DESC"
		      , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_EXEC WHERE DTA_INICIO BETWEEN ?1 AND ?2"
		      , nativeQuery = true)
	Page<Execucoes> findByDtaInicio(Date dtaInicio, Date dtaFim, Pageable pageable);
	
	@Query( value = "SELECT * FROM TOPT_LINX_EXEC WHERE ORIGEM = ?1 ORDER BY ID DESC"
		      , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_EXEC WHERE ORIGEM = ?1"
		      , nativeQuery = true)
	Page<Execucoes> findByOrigem(Integer origemExecucao, Pageable pageable);
	
	@Query( value = "SELECT * FROM TOPT_LINX_EXEC WHERE CADASTRO = ?1 ORDER BY ID DESC"
		      , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_EXEC WHERE CADASTRO = ?1"
		      , nativeQuery = true)
	Page<Execucoes> findByCadastro(String Cadastro, Pageable pageable);
	
}