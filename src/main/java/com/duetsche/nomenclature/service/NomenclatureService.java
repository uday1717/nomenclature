package com.duetsche.nomenclature.service;

import java.util.Optional;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.model.Nomenclature;

public interface NomenclatureService {

	Nomenclature saveNomenclature(Nomenclature nomenclature);
	Optional<Nomenclature> getNomenclatureByOrderId(Long orderId) throws ResourceNotFoundException;
}
