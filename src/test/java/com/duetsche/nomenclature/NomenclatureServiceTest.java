package com.duetsche.nomenclature;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duetsche.nomenclature.exception.ResourceNotFoundException;
import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.repository.NomenclatureRepository;
import com.duetsche.nomenclature.service.NomenclatureServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class NomenclatureServiceTest {

	@Mock
	private NomenclatureRepository nomenclatureRepository;

	@InjectMocks
	private NomenclatureServiceImpl nomenclatureservice;

	private Nomenclature nomenclature;

	@BeforeAll
	public void setup() {

		nomenclature = new Nomenclature(389412L, 1, "B", "1", "Crop and animal production",
				"division includes two basic activities", "division also includes service activities", "NAME",
				"Agricultural activities exclude", "A");
	}

	@DisplayName("JUnit test for saveNomenclature")
	@Test
	public void saveNomenclature() {
		// given
		given(nomenclatureRepository.save(nomenclature)).willReturn(nomenclature);

		// when
		nomenclatureservice.saveNomenclature(nomenclature);

		// then
		assertThat(nomenclature).isNotNull();
	}

	@DisplayName("JUnit test for getNomenclatureByOrderId")
	@Test
	public void getNomenclatureByOrderId() throws ResourceNotFoundException {
		// given
		given(nomenclatureRepository.findById(nomenclature.getOrderId())).willReturn(Optional.of(nomenclature));

		// when
		Nomenclature nomenclatureResult = nomenclatureservice.getNomenclatureByOrderId(nomenclature.getOrderId()).get();

		// then
		assertThat(nomenclatureResult).isNotNull();
		assertThat(nomenclatureResult.getOrderId()).isEqualTo(nomenclature.getOrderId());
	}

	@DisplayName("JUnit test for getNomenclatureByOrderId which throws exception")
	@Test
	public void getNomenclatureErrorMessage(){
		// given
		nomenclature.setOrderId(39899911L);
		given(nomenclatureRepository.findById(nomenclature.getOrderId())).willReturn(Optional.of(nomenclature));

		// when - assert when exception occurred.
		try {
			nomenclatureservice.getNomenclatureByOrderId(nomenclature.getOrderId());
		} catch (ResourceNotFoundException ex) {
			assertThat("No order record exist for given id").isEqualTo(ex.getMessage());
		}
	}

	@AfterAll
	public void tearDown() {

		nomenclature = null;
	}
}
