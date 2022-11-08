package com.duetsche.nomenclature.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duetsche.nomenclature.model.Nomenclature;

@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature, Long>{
	
}
