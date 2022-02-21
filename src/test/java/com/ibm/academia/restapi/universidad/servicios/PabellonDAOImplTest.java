package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

@SpringBootTest
public class PabellonDAOImplTest {

	@MockBean
	private PabellonRepository pabellonRepository;
	
	@Autowired
	private PabellonDAO pabellonDao;
	
	@Test
	@Disabled
	@DisplayName("Test: buscar pabellon por localidad")
	void findPabellonesByDireccionLocalidad() {
		
		//When
		pabellonDao.findPabellonesByDireccionLocalidad(anyString());
		
		//Then
		verify(pabellonRepository).findPabellonesByDireccionLocalidad(anyString());
		
	}
	
	@Test
	//@Disabled
	@DisplayName("Test: buscar pabellon por nombre de pabellon")
	void findPabellonesByNombre() {
		
		//When
		pabellonDao.findPabellonesByNombre(anyString());
		
		//Then
		verify(pabellonRepository).findPabellonesByNombre(anyString());
		
	}
	
}
