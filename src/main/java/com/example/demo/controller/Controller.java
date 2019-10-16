package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.AnimalInfo;
import com.example.demo.model.Cow;
import com.example.demo.model.CowBcs;
import com.example.demo.model.Herd;
import com.example.demo.model.HerdInfo;
import com.example.demo.service.RestService;

//Definicion de get, post, delete, put


@RestController
public class Controller {
	
	@Autowired
	RestService rs;
	
	//Obtener informacion de un determinado herd
	@GetMapping(path = "/api/herd/{id}/")
	public ResponseEntity<HerdInfo> getHerd(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findHerdById(id));
	}
	
	//Obtener informacion de una determinada vaca
	@GetMapping(path = "/api/cow/{id}/")
	public ResponseEntity<AnimalInfo> getCow(@PathVariable(value = "id") long id ){
		return ResponseEntity.ok(rs.findCowById(id));
	}
	
	//Cargar un herd
	@PostMapping(path = "/api/herd/")
	public ResponseEntity<Herd> registerHerd(@RequestBody Herd h){
		return ResponseEntity.ok(rs.herdSave(h));
	}
	
	//Cargar una vaca
	@PostMapping(path = "/api/cow/")
	public ResponseEntity<Cow> registerCow(@RequestBody Cow c){
		return ResponseEntity.ok(rs.cowSave(c));
	}
	
	//Cargar una Bcs
	@PostMapping(path = "/api/bcs/")
	public ResponseEntity<CowBcs> registerCowBcs(@RequestBody CowBcs c){
		return ResponseEntity.ok(rs.cowBcsSave(c));
	}
	
	//Cargar alerta para herd
	/*@PostMapping(path = "/api/alertHerd/")
	public ResponseEntity<CowBcs> registerCow(@RequestBody CowBcs c){
		return ResponseEntity.ok(rs.cowBcsSave(c));
	}
	
	//Cargar alerta para cow
	@PostMapping(path = "/api/alertCow/")
	public ResponseEntity<CowBcs> registerCow(@RequestBody CowBcs c){
		return ResponseEntity.ok(rs.cowBcsSave(c));
	}
	*/
	
}
