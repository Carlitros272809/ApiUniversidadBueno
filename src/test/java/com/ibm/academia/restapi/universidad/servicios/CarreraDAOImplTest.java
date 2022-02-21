package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ibm.academia.restapi.universidad.datos.DatosDummy.*;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;

public class CarreraDAOImplTest {
	
	CarreraDAO carreraDao;
	CarreraRepository carreraRepository;
	
	@BeforeEach
	void setUp(){
		carreraRepository = mock(CarreraRepository.class);
		carreraDao = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por nombre")
	void findCarrerasByNombreContains() {

		//Given
		String nombreCarrera = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContains(nombreCarrera))
		.thenReturn(Arrays.asList(carrera01(),carrera03()));
		
		//When
		List <Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContains(nombreCarrera);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(carrera01());
		assertThat(expected.get(1)).isEqualTo(carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContains(nombreCarrera);
		
	}
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por nombre Ignore Case")
	void findCarrerasByNombreContainsIgnoreCase() {
		//Given
		String nombreCarrera = "Ingenieria";
		when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombreCarrera))
		.thenReturn(Arrays.asList(carrera01(),carrera03()));
		
		//When
		List <Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombreCarrera);
		
		//Then
		assertThat(expected.get(0)).isEqualTo(carrera01());
		assertThat(expected.get(1)).isEqualTo(carrera03());
		
		verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombreCarrera);

	}
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar carreras por cantidad de anios mayores")
	void findCarrerasByCantidadAniosAfter() {
		
		//Given
        Integer cantidad = 4;
        when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad))
                .thenReturn(Arrays.asList(carrera01(), carrera03()));

        //When
        List<Carrera> expected = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(cantidad);

        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera03());

        verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);

	}
	
	@Test
	//@Disabled
	@DisplayName("Test: buscar carreras por profesor nombre y apellido")
	void buscarCarrerasPorProfesorNombreYApellido() {
		
		//Given
		when(carreraRepository.buscarCarrerasPorProfesorNombreYApellido(anyString(),anyString()))
		.thenReturn(Arrays.asList(carrera01(),carrera03()));
		
		//When
		List<Carrera> expected = (List<Carrera>) carreraDao.buscarCarrerasPorProfesorNombreYApellido(anyString(), anyString());
		
        //Then
        assertThat(expected.get(0)).isEqualTo(carrera01());
        assertThat(expected.get(1)).isEqualTo(carrera03());
		
	}
}
