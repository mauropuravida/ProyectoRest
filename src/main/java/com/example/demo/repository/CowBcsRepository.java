package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;

@RepositoryRestResource(exported = false)
public interface CowBcsRepository extends PagingAndSortingRepository<CowBcs, Long>{
	
	CowBcs findFirstBycowIdOrderByFechaDesc(Cow c);
	CowBcs save(CowBcs c);

}
