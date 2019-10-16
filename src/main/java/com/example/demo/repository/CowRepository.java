package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Cow;
import com.example.demo.model.Herd;

@RepositoryRestResource(exported = false)
public interface CowRepository extends PagingAndSortingRepository<Cow, Long>{
	
	List<Cow> findAllByherd(Herd herd);
	Cow findById(long id);

}