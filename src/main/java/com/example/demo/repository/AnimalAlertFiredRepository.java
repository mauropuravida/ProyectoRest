package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.AnimalAlertFired;
import com.example.demo.model.Cow;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface AnimalAlertFiredRepository extends PagingAndSortingRepository<AnimalAlertFired, Long>{
	List<AnimalAlertFired> findByCow(Cow cow);
	

}
