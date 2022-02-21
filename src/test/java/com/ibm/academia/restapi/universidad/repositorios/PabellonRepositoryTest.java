package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class PabellonRepositoryTest {

	@Autowired
	@Qualifier("repositorioPabellon")
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		//Given 
		Pabellon pabellon1 = pabellonRepository.save(DatosDummy.pabellon01());
		Pabellon pabellon2 = pabellonRepository.save(DatosDummy.pabellon02());
	}
	
	@AfterEach
	void tearDown() {
		pabellonRepository.deleteAll();
	}
	
	@Test
	//@Disabled
	@DisplayName("Test: buscar Pabellones por localidad")
	void findPabellonesByDireccionLocalidad(){
		//When
		String nombreLocalidad = "Mexico";
		List<Pabellon> expected = (List<Pabellon>)((PabellonRepository)pabellonRepository).findPabellonesByDireccionLocalidad(nombreLocalidad);
		
		//Then
		assertThat(expected.size() == 2).isTrue();
		
	}
	
	@Test
	@Disabled
	@DisplayName("Test: buscar pabellones por nombre")
	void findPabellonesByNombre() {
		//When
		String nombrePabellon = "Prisoners";
		List<Pabellon> expected = (List<Pabellon>)((PabellonRepository)pabellonRepository).findPabellonesByNombre(nombrePabellon);
		
		//Then
		assertThat(expected.size() == 1).isTrue();
	}
}
