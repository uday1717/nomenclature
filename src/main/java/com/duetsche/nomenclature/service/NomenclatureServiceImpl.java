package com.duetsche.nomenclature.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.helper.CSVHelper;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.repository.NomenclatureRepository;

@Service
public class NomenclatureServiceImpl implements NomenclatureService {

	@Autowired
	NomenclatureRepository nomenclatureRepository;

	
	@Override
	public Nomenclature saveNacDetails(Nomenclature nomenclature) {
		return nomenclatureRepository.save(nomenclature);
	}

	@Override
	public Optional<Nomenclature> getNacDetailsByOrderId(Long orderId) throws ResourceNotFoundException {
		Optional<Nomenclature> nomenclature = nomenclatureRepository.findById(orderId);
		if (nomenclature.isPresent()) {
			return nomenclature;
		} else {
			throw new ResourceNotFoundException("No Nac record exists for the given id");
		}
	}

	@Override
	public void uploadNacDetails(MultipartFile file) {
		try {
			List<Nomenclature> tutorials = CSVHelper.csvToNacDetails(file.getInputStream());
			nomenclatureRepository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("failed to store csv data: " + e.getMessage());
		}
	}
}
