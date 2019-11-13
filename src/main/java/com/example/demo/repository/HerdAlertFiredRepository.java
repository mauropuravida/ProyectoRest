package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.HerdAlertFired;
import com.example.demo.model.Herd;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface HerdAlertFiredRepository extends PagingAndSortingRepository<HerdAlertFired, Long> {

	List<HerdAlertFired> findByHerd(Herd herd);
}
