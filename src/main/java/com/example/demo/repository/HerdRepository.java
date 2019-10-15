package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Cow;
import com.example.demo.model.Herd;

//Esta clase define metos pre implementados de busqueda en la base de datos

@RepositoryRestResource(exported = false)
public interface HerdRepository extends PagingAndSortingRepository<Herd, Long>{
	
	Herd findById(long id);

}
