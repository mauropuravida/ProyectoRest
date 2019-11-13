package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AnimalAlert;
import com.example.demo.model.AnimalAlertFired;
import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdAlert;
import com.example.demo.model.HerdAlertFired;
import com.example.demo.model.HerdInfo;
import com.example.demo.service.RestService;

import com.example.demo.exceptions.NotFoundException;
import load.AnimalAlertLoad;
import load.CowBcsLoad;
import load.CowLoad;
import load.HerdAlertLoad;
import load.HerdLoad;
import load.Session;

//Definicion de get, post, delete, put


@RestController
public class Controller {
	
	@Autowired
	RestService rs;
	
	//Obtener informacion de un determinado herd
	@GetMapping(path = "/api/herd/{id}")
	public ResponseEntity<HerdInfo> getHerd(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findInfoHerdById(id));
	}
	
	//Obtener informacion de una determinada vaca
	@GetMapping(path = "/api/cow/{id}")
	public ResponseEntity<AnimalInfo> getCow(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findInfoCowById(id));
	}
	
	//Cargar un herd
	@PostMapping(path = "/api/herd")
	public ResponseEntity<Herd> registerHerd(@RequestBody HerdLoad h){
		Herd herd = new Herd();
		herd.setLocation(h.getLocation());
		return ResponseEntity.ok(rs.herdSave(herd));
	}
	
	//Cargar una vaca
	@PostMapping(path = "/api/cow")
	public ResponseEntity<Cow> registerCow(@RequestBody CowLoad c){
		Herd herd = rs.findHerdById(c.getHerdId());
		
		if(herd == null) {
			throw new NotFoundException("El id de herd no existe en la base de datos");
		}
		
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
	@PostMapping(path = "/api/bcs")
	public ResponseEntity<CowBcs> registerCowBcs(@RequestBody CowBcsLoad c){
		Cow cow = rs.findCowById(c.getCowId());
		
		if(cow == null) {
			throw new NotFoundException("El id de cow no existe en la base de datos");
		}
		
		CowBcs bcs = new CowBcs();
		
		bcs.setCc(c.getCc());
		bcs.setCowId(cow);
		bcs.setFecha(c.getFecha());
		
		return ResponseEntity.ok(rs.cowBcsSave(bcs));
	}
	
	//Cargar alerta para herd alert
	@PostMapping(path = "/api/herdAlert")
	public ResponseEntity<HerdAlert> registerHerdAlert(@RequestBody HerdAlertLoad h){
		Herd herd = rs.findHerdById(h.getHerdId());
		
		if(herd == null) {
			throw new NotFoundException("El id de herd no existe en la base de datos");
		}
		
		HerdAlert alert = new HerdAlert();
		alert.setBcsThresholdMax(h.getBcsThresholdMax());
		alert.setBcsThresholdMin(h.getBcsThresholdMin());
		alert.setHerd(herd);
		
		return ResponseEntity.ok(rs.herdAlertSave(alert));
	}
	
	//Cargar alerta para cow alert
	@PostMapping(path = "/api/cowAlert")
	public ResponseEntity<AnimalAlert> registerCowAlert(@RequestBody AnimalAlertLoad c){
		Cow cow = rs.findCowById(c.getCowId());
		
		if(cow == null) {
			throw new NotFoundException("El id de cow no existe en la base de datos");
		}
		
		AnimalAlert animalA = new AnimalAlert();
		animalA.setBcsThresholdMax(c.getBcsThresholdMax());
		animalA.setBcsThresholdMin(c.getBcsThresholdMin());
		animalA.setAnimal(cow);
		
		return ResponseEntity.ok(rs.animalAlertSave(animalA));
	}
	
	@GetMapping(path="/api/cowFiredAlert")
	public ResponseEntity<List<AnimalAlertFired>> getCowsFiredAlert(){
		return ResponseEntity.ok(rs.getCowsFiredAlert());
	}
	
	@GetMapping(path="/api/herdFiredAlert")
	public ResponseEntity<List<HerdAlertFired>> getHerdsFiredAlert(){
		return ResponseEntity.ok(rs.getHerdsFiredAlert());
	}
	
	@PostMapping (path = "/api/session")
	public ResponseEntity<Boolean> setEnableSession(@RequestBody Session session){
		return ResponseEntity.ok(rs.setSession(session.isEnable()));
	}
	
	
}
