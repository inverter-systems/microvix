package com.topinternacional.linx.model.nl.ai.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.nl.ai.AiNsNota;

@Repository
public interface AiNsNotasRepository extends CrudRepository<AiNsNota, Long> {

	@Query( value = "SELECT * FROM AI_NS_NOTAS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2"
		      , countQuery = "SELECT COUNT(*) FROM AI_NS_NOTAS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2"
		      , nativeQuery = true)
	List<AiNsNota> findByCodUnidadeAndNumDocumento(Integer codUnidade, Integer numDocumento);

	
	@Query( value = "SELECT NUM_SEQ FROM NS_NOTAS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2 AND COD_SERIE=?3"
		      , countQuery = "SELECT COUNT(*) FROM NS_NOTAS WHERE COD_UNIDADE = ?1 AND NUM_NOTA=?2 AND COD_SERIE=?3"
		      , nativeQuery = true)
	Integer getNumSeqNota(Integer codUnidade, Integer numero, String serie);
	
	@Procedure(value = "NS_EXCLUI_NOTA_SP")
	void excluiNota(Integer numSeq, int i, int j, int k, int l, int m, int n, int o, int p, int q, int r, int s, int t, int u, int v, int w, int x);
	
}