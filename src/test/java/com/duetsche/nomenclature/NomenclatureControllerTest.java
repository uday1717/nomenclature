package com.duetsche.nomenclature;

import static org.mockito.Mockito.when;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.duetsche.nomenclature.model.Nomenclature;
import com.duetsche.nomenclature.repository.NomenclatureRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NomenclatureControllerTest.class)
@TestInstance(Lifecycle.PER_CLASS)
@ComponentScan(basePackages = "com.duetsche.nomenclature")
public class NomenclatureControllerTest {

	@MockBean
	private NomenclatureRepository nomenclatureRepo;

	private Nomenclature nomenclature;

	@BeforeAll
	public void setup() {

		nomenclature = new Nomenclature(389112L, 2, "A", "1", "Growing of grapes",
				"growing of wine grapes and table grapes in vineyards", "division also includes service activities",
				"NAME", "Agricultural activities exclude", "121");
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	void testNacRecordCreate() throws Exception {
		when(nomenclatureRepo.save(nomenclature)).thenReturn(nomenclature);
		mockMvc.perform(post("/api/nomenclature").contentType("application/json")
				.content(objectMapper.writeValueAsString(nomenclature))).andExpect(status().isCreated()).andDo(print());
	}
	
	@Test
	void testNacRecordExists() throws Exception {
		testNacRecordCreate();
		when(nomenclatureRepo.findById(nomenclature.getOrderId())).thenReturn(Optional.of(nomenclature));
		mockMvc.perform(get("/api/nomenclature/{orderId}", nomenclature.getOrderId())).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void testNacRecordNotExists() throws Exception {
		long id = 123L;

		when(nomenclatureRepo.findById(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/nomenclature/{orderId}", id)).andExpect(status().isNotFound()).andDo(print());
	}
	
	@Test
	public void testNacUploadFile() throws Exception {
		MockMultipartFile nacfile = new MockMultipartFile("file", "NACE_REV2_20221103_144244.csv", "text/csv",
				this.getClass().getResourceAsStream("/NACE_REV2_20221103_144244.csv"));
		mockMvc.perform(MockMvcRequestBuilders.multipart("/api/nomenclature/upload").file(nacfile))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	 
}
