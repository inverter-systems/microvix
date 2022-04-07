package com.topinternacional.linx.model.admin.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.topinternacional.linx.model.admin.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {

	Page<Log> findAll(Pageable pageable);
	//Page<Log> findByExecIdOrderById(Long execId, Pageable pageable);
	
	@Query( value = "SELECT e.* FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1"
		  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1"
		  , nativeQuery = true)
	Page<Log> findByFilters(Long id,  Pageable pageable);
	
	
	@Query( value = "SELECT COUNT(*) FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1 and e.erro is null"
		  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1 and e.erro is null"
          , nativeQuery = true)
	Double findSucessPercent(Long id);


	@Query( value = "SELECT e.* FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1 and e.erro is not null order by id"
			  , countQuery = "SELECT COUNT(*) FROM TOPT_LINX_LOG e WHERE e.exec_Id = ?1 and e.erro is not null order by id"
			  , nativeQuery = true)
	List<Log> findAllErros(Long id);
	
}