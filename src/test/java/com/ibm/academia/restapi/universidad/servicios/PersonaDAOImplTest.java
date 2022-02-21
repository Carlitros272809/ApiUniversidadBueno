package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;

@SpringBootTest
public class PersonaDAOImplTest {
	
	@MockBean
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoDAO alumnoDao;
	
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@MockBean
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private ProfesorDAO profesorDao;
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar personas por nombre y apellido")
	void buscarPorNombreYApellido() {
		//When 
		alumnoDao.buscarPorNombreYApellido(anyString(), anyString());
		
		//Then
		verify(alumnoRepository).buscarPorNombreYApellido(anyString(), anyString());
	}
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar personas por dni")
	void buscarPorDni () {
		//When 
		empleadoDao.buscarPorDni(anyString());
		
		//Then
		verify(empleadoRepository).buscarPorDni(anyString());
	}
	
	@Test
	@Disabled
	@DisplayName("Test: buscar personas por apellido solamente")
	void buscarPersonaPorApellido () {
		//When 
		profesorDao.buscarPersonaPorApellido(anyString());
		
		//Then
		verify(profesorRepository).buscarPersonaPorApellido(anyString());
	}
}
