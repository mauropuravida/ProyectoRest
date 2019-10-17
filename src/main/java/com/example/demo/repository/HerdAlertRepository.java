package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Herd;
import com.example.demo.model.HerdAlert;

@RepositoryRestResource(exported = false)
public interface HerdAlertRepository extends PagingAndSortingRepository<HerdAlert, Long>{
	
	HerdAlert findByherd(Herd h);
	HerdAlert save(HerdAlert h);
}
