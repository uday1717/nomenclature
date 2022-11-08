package com.duetsche.nomenclature.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.repository.NomenclatureRepository;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

	@Autowired
	NomenclatureRepository nomenclatureRepository;

	@Override
	public Nomenclature saveNomenclature(Nomenclature nomenclature) {
		return nomenclatureRepository.save(nomenclature);
	}

	@Override
	public Optional<Nomenclature> getNomenclatureByOrderId(Long orderId) throws ResourceNotFoundException {
		Optional<Nomenclature> nomenclature = nomenclatureRepository.findById(orderId);
		if (nomenclature.isPresent()) {
			return nomenclature;
		} else {
			throw new ResourceNotFoundException("No order record exist for given id");
		}
	}
}
