package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Cow;

@RepositoryRestResource(exported = false)
public interface CowRepository extends PagingAndSortingRepository<Cow, Long>{
	
	List<Cow> findAllByherdId(long id);
	Cow findById(long id);

}
