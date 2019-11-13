package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.AnimalAlert;
import com.example.demo.model.Cow;

@RepositoryRestResource(exported = false)
public interface AnimalAlertRepository  extends PagingAndSortingRepository<AnimalAlert, Long>{
	
	AnimalAlert findBycow(Cow c);
	AnimalAlert findByCowId(long id);
	AnimalAlert save(AnimalAlert aa);

}