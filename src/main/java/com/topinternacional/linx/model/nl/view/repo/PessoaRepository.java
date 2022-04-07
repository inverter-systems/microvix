package com.topinternacional.linx.model.nl.view.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.topinternacional.linx.model.nl.view.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	Page<Pessoa> findAll(Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PESSOAS e WHERE e.tipopessoa = ?1"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PESSOAS e WHERE e.tipopessoa = ?1"
		      , nativeQuery = true)
	Page<Pessoa> findAllPessoas(String tipopessoa, PageRequest pr);
	
	
	Page<Pessoa> findByDocCliente(String docCliente, Pageable pageable);
	Page<Pessoa> findByCodigo(String codigo, Pageable pageable);
	
	@Query("SELECT e FROM Pessoa e WHERE e.nomeRazaoSocial LIKE %?1%")
	Page<Pessoa> findByNomeRazaoSocial(String nome, Pageable pageable);
	
	@Query("SELECT e FROM Pessoa e WHERE e.codigo = ?1 or e.nomeRazaoSocial LIKE %?2% or e.pfPj =?3")
	Page<Pessoa> findByCodigoOrNomeRazaoSocialOrPfPj(String codigo, String nomeRazaoSocial, String pfpj, Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PESSOAS e WHERE e.pf_pj = ?1 AND e.tipopessoa = ?2"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PESSOAS e WHERE e.pf_pj = ?1 AND e.tipopessoa = ?2"
		      , nativeQuery = true)	
	Page<Pessoa> findAllByFilter(String tipo, String tipopessoa, PageRequest pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PESSOAS e WHERE e.NOME_RAZAO_SOCIAL like '%'||?1||'%' AND e.tipopessoa = ?2"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PESSOAS e WHERE e.NOME_RAZAO_SOCIAL like '%'||?1||'%' AND e.tipopessoa = ?2"
		      , nativeQuery = true)
	Page<Pessoa> findAllByName(String nome, String tipopessoa, PageRequest pageable);

	@Query( value = "SELECT e.* FROM TOPV_LINX_PESSOAS e WHERE e.codigo = ?1 AND e.tipopessoa = ?2"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PESSOAS e WHERE e.codigo = ?1 AND e.tipopessoa = ?2"
		      , nativeQuery = true)	
	Page<Pessoa> findAllByCodigo(String codigo, String tipopessoa, PageRequest pr);
	
	
}

