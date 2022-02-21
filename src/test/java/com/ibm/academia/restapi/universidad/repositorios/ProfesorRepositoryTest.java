package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {
	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	//@Disabled
	@DisplayName("Test: Buscar profesores por carrera")
	void findProfesoresByCarrera() {
		
		//Given
        Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());
        
        Carrera carrera = carreraRepository.save(DatosDummy.carrera01());
		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(carrera);
        ((Profesor)personaProfesor).setCarreras(carreras);
		
		//When
		String carreraNombre = carrera.getNombre();
		List<Persona> expected = (List<Persona>) ((ProfesorRepository)profesorRepository).findProfesoresByCarrera(carreraNombre);
	
		//Then
		assertThat(expected.size() == 1).isTrue();
	}
}
