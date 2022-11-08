package com.duetsche.nomenclature;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.repository.NomenclatureRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class NomenclatureRepositoryTest {

	@Autowired
	private NomenclatureRepository nomenclatureRepo;
	
	private Nomenclature nomenclature;

	@BeforeAll
	public void setup() {

		nomenclature = new Nomenclature(389112L, 2, "A", "1", "Growing of grapes",
				"growing of wine grapes and table grapes in vineyards", "division also includes service activities",
				"NAME", "Agricultural activities exclude", "121");
	}

	@Test
	public void saveNomenclature() {
		nomenclature = nomenclatureRepo.save(nomenclature);
		assertThat(nomenclature).isNotNull();
	}

	@Test
	public void isNomenclatureOrderExists() {
		Optional<Nomenclature> actualResult = nomenclatureRepo.findById(nomenclature.getOrderId());
		assertThat(actualResult).isPresent();
		assertThat(actualResult.get().getOrderId()).isEqualTo(nomenclature.getOrderId());
	}

	@Test
	public void whenNomenclatureNotExists_thenAssert() {
		nomenclature.setOrderId(1234L);
		Optional<Nomenclature> actualResult = nomenclatureRepo.findById(nomenclature.getOrderId());
		assertThat(actualResult).isEmpty();
	}
	
	@AfterAll
	public void tearDown() {

		nomenclature = null;
	}

}
