package com.topinternacional.linx.model.nl.view.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.view.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	Page<Produto> findAll(Pageable pageable);	
	
	@Query( value = "SELECT * FROM TOPV_LINX_PRODUTOS"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PRODUTOS"
		      , nativeQuery = true)
	Page<Produto> findByINVERTER(Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PRODUTOS e WHERE e.codigo = ?1 or e.cod_marca = ?2 or e.cod_setor = ?3"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PRODUTOS e WHERE e.codigo = ?1 or e.cod_marca = ?2 or e.cod_setor = ?3"
		      , nativeQuery = true)
	Page<Produto> findByFilters(String codigo,  String marca,  String setor,  Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PRODUTOS e WHERE e.codigo = ?1"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PRODUTOS e WHERE e.codigo = ?1"
		      , nativeQuery = true)
	Page<Produto> findByFilters(String codigo, PageRequest pag);
	
	@Query( value = "SELECT e.* FROM TOPV_LINX_PRODUTOS e WHERE e.cod_marca = ?1 and e.cod_setor = ?2"
		      , countQuery = "SELECT COUNT(*) FROM TOPV_LINX_PRODUTOS e WHERE e.cod_marca = ?1 and e.cod_setor = ?2"
		      , nativeQuery = true)
	Page<Produto> findByFilters(String marca,  String setor, PageRequest pag);
	
	Page<Produto> findByCodigoOrCodFornecedorOrCodMarca(String codigo, String codFornecedor, String codMarca, Pageable pageable);

	
}