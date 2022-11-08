package com.duetsche.nomenclature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScans({ @ComponentScan("com.duetsche.nomenclature.controller") })
@EnableJpaRepositories("com.duetsche.nomenclature.repository")
@EntityScan("com.duetsche.nomenclature.model")
public class NomenclatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(NomenclatureApplication.class, args);
	}

}
