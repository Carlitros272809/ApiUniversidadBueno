package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
public class CarreraRepositoryTest {

	@Autowired
	private CarreraRepository carreraRepository;

	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;

	/*
	 * @BeforeEach
	 * void setUp() {
	 * //Given
	 * carreraRepository.save(DatosDummy.carrera01());
	 * carreraRepository.save(DatosDummy.carrera02());
	 * carreraRepository.save(DatosDummy.carrera03());
	 * }
	 * 
	 * @AfterEach
	 * void tearDown() {
	 * carreraRepository.deleteAll();
	 * }
	 */

	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarrerasByNombreContains() {

		// When
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContains("Industrial");

		// Then
		assertThat(expected.size() == 1).isTrue();
	}

	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por nombre Ignore Case")
	void findCarrerasByNombreContainsIgnoreCase() {

		// When
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByNombreContainsIgnoreCase("SISTEMAS");

		// Then
		assertThat(expected.size() == 2).isTrue();

	}

	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por cantidad de anios mayores")
	void findCarrerasByCantidadAniosAfter() {

		// When
		List<Carrera> expected = (List<Carrera>) carreraRepository.findCarrerasByCantidadAniosAfter(4);

		// Then
		assertThat(expected.size() == 2).isTrue();
	}

	@Test
	// @Disabled
	@DisplayName("Test: buscar carreras por profesor nombre y apellido")
	void buscarCarrerasPorProfesorNombreYApellido() {

		// Given
		Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());

		Carrera sistemas = carreraRepository.save(DatosDummy.carrera01());
		Carrera industrial = carreraRepository.save(DatosDummy.carrera02());
		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(sistemas);
		carreras.add(industrial);
		((Profesor) personaProfesor).setCarreras(carreras);

		// When
		List<Carrera> expected = (List<Carrera>) carreraRepository
				.buscarCarrerasPorProfesorNombreYApellido(personaProfesor.getNombre(), personaProfesor.getApellido());

		// Then
		assertThat(expected.size() == 2).isTrue();
	}

}
