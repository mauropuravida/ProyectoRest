package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AnimalAlert;
import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdAlert;
import com.example.demo.model.HerdInfo;
import com.example.demo.service.RestService;

import load.AnimalAlertLoad;
import load.CowBcsLoad;
import load.CowLoad;
import load.HerdAlertLoad;
import load.HerdLoad;

//Definicion de get, post, delete, put


@RestController
public class Controller {
	
	@Autowired
	RestService rs;
	
	//Obtener informacion de un determinado herd
	@GetMapping(path = "/api/herd/{id}/")
	public ResponseEntity<HerdInfo> getHerd(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findInfoHerdById(id));
	}
	
	//Obtener informacion de una determinada vaca
	@GetMapping(path = "/api/cow/{id}/")
	public ResponseEntity<AnimalInfo> getCow(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findInfoCowById(id));
	}
	
	//Cargar un herd
	@PostMapping(path = "/api/herd/")
	public ResponseEntity<Herd> registerHerd(@RequestBody HerdLoad h){
		Herd herd = new Herd();
		herd.setLocation(h.getLocation());
		return ResponseEntity.ok(rs.herdSave(herd));
	}
	
	//Cargar una vaca
	@PostMapping(path = "/api/cow/")
	public ResponseEntity<Cow> registerCow(@RequestBody CowLoad c){
		Herd herd = rs.findHerdById(c.getHerdId());
		Cow cow = new Cow();
		
		cow.setCantidadPartos(c.getCantidadPartos());
		cow.setElectronicId(c.getElectronicId());
		cow.setFechaNacimiento(c.getFechaNacimiento());
		cow.setHerd(herd);
		cow.setPeso(c.getPeso());
		cow.setUltimaFechaParto(c.getUltimaFechaParto());
		return ResponseEntity.ok(rs.cowSave(cow));
	}
	
	//Cargar una Bcs
	@PostMapping(path = "/api/bcs/")
	public ResponseEntity<CowBcs> registerCowBcs(@RequestBody CowBcsLoad c){
		Cow cow = rs.findCowById(c.getCowId());
		CowBcs bcs = new CowBcs();
		
		bcs.setCc(c.getCc());
		bcs.setCowId(cow);
		bcs.setFecha(c.getFecha());
		
		return ResponseEntity.ok(rs.cowBcsSave(bcs));
	}
	
	//Cargar alerta para herd alert
	@PostMapping(path = "/api/herdAlert/")
	public ResponseEntity<HerdAlert> registerHerdAlert(@RequestBody HerdAlertLoad h){
		Herd herd = rs.findHerdById(h.getHerdId());
		
		HerdAlert alert = new HerdAlert();
		alert.setBcsThresholdMax(h.getBcsThresholdMax());
		alert.setBcsThresholdMin(h.getBcsThresholdMin());
		alert.setHerd(herd);
		
		return ResponseEntity.ok(rs.herdAlertSave(alert));
	}
	
	//Cargar alerta para cow alert
	@PostMapping(path = "/api/cowAlert/")
	public ResponseEntity<AnimalAlert> registerCowAlert(@RequestBody AnimalAlertLoad c){
		Cow cow = rs.findCowById(c.getCowId());
		
		AnimalAlert animalA = new AnimalAlert();
		animalA.setBcsThresholdMax(c.getBcsThresholdMax());
		animalA.setBcsThresholdMin(c.getBcsThresholdMin());
		animalA.setAnimal(cow);
		
		return ResponseEntity.ok(rs.animalAlertSave(animalA));
	}
	
}
