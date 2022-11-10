package com.duetsche.nomenclature.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.model.Nomenclature;

public interface NomenclatureService {

	void uploadNacDetails(MultipartFile file);
	Optional<Nomenclature> getNacDetailsByOrderId(Long orderId) throws ResourceNotFoundException;
	Nomenclature saveNacDetails(Nomenclature nomenclature);
}
