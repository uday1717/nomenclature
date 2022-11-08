package com.duetsche.nomenclature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.service.NomenclatureServiceImpl;

@RestController
public class NomenclatureController {

	@Autowired
	NomenclatureServiceImpl nomenclatureService;
	
	private Nomenclature nomenclature;
	
	@PostMapping("/nomenclature")
	public ResponseEntity<Nomenclature> putNacDetails(@RequestBody Nomenclature nomenclature) {
		nomenclature =  nomenclatureService.saveNomenclature(nomenclature);
		return new ResponseEntity<Nomenclature>(nomenclature, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/nomenclature/{orderId}")
	public ResponseEntity<Nomenclature> getNacDetails(@PathVariable Long orderId)throws ResourceNotFoundException {
		nomenclature =  nomenclatureService.getNomenclatureByOrderId(orderId).get();
		return new ResponseEntity<Nomenclature>(nomenclature, new HttpHeaders(), HttpStatus.OK);
	}
}
