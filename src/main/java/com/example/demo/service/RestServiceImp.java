package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdInfo;
import com.example.demo.repository.CowBcsRepository;
import com.example.demo.repository.CowRepository;
import com.example.demo.repository.HerdRepository;

@Service
public class RestServiceImp implements RestService{

	@Autowired
	HerdRepository repo;
	
	@Autowired
	CowRepository cr;
	
	@Autowired
	CowBcsRepository cbr;

	//Devuelve la informacion de un herd, que incluye la informacion de cada una de sus vacas
	@Override
	public HerdInfo findHerdById(long id) {
		
		float prom = 0;
		int divisor = 0;
		
		HerdInfo he = new HerdInfo();
		he.setId(repo.findById(id).getId());
		he.setLocation(repo.findById(id).getLocation());
		
		List<AnimalInfo> ai = new ArrayList<AnimalInfo>();
		List<Cow> cows = cr.findAllByherd(repo.findById(id));
		for (Cow cow : cows) {
			AnimalInfo aiAux = findCowById(cow.getId());

			if (aiAux.getFechaBcs() != null) {				
				prom+=aiAux.getCc();
				divisor++;
			}
			
			ai.add(aiAux);
		}
		
		if(prom >0)
			prom= prom/divisor;
		
		he.setBcsPromedio(prom);
		he.addAnimalsInfo(ai);
		
		return he;
	}

	//Devuelve toda la informacion de un animal
	@Override
	public AnimalInfo findCowById(long id) {
		
		Cow cow = cr.findById(id);
		
		AnimalInfo aiAux = new AnimalInfo();
		aiAux.setId(cow.getId());
		aiAux.setElectronicId(cow.getElectronicId());
		aiAux.setFechaNacimiento(cow.getFechaNacimiento());
		aiAux.setUltimaFechaParto(cow.getUltimaFechaParto());
		aiAux.setCantidadPartos(cow.getCantidadPartos());
		aiAux.setPeso(cow.getPeso());
		aiAux.setHerdId(cow.getHerdId());
		
		CowBcs cbrr = cbr.findFirstBycowIdOrderByFechaDesc(cow);
		if (cbrr != null) {
			aiAux.setCc(cbrr.getCc());
			aiAux.setCowBcsId(cbrr.getId());
			aiAux.setFechaBcs(cbrr.getFecha());
		}
		
		return aiAux;
	}

	@Override
	public Herd herdSave(Herd h) {
		return repo.save(h);
	}

	@Override
	public Cow cowSave(Cow c) {
		return cr.save(c);
	}

	@Override
	public CowBcs cowBcsSave(CowBcs c) {
		//aca se deben controlar alertas
		return cbr.save(c);
	}

}
