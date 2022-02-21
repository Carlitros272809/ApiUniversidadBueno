package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class PersonaRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository alumnoRepository;
	
	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository empleadoRepository;
	
	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;
	
	@Test
	@Disabled
	@DisplayName("Test: Buscar por Nombre y Apellido")
	void buscarPorNombreYApellido()
	{
		//Given
		Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());
		
		//When
		Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(), DatosDummy.empleado01().getApellido());
		
		//Then
		assertThat(expected.get()).isInstanceOf(Empleado.class);
		assertThat(expected.get()).isEqualTo(personaEmpleado);
		
	}
	
	@Test
	@Disabled
	@DisplayName("Test: buscar por dni")
	void buscarPorDni(){
		//Given
        Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());

        //When
        Optional<Persona> expected = profesorRepository.buscarPorDni(DatosDummy.profesor01().getDni());

        //Then
        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(personaProfesor);
        assertThat(expected.get().getDni()).isEqualTo(personaProfesor.getDni());
	}
	
	@Test
	@Disabled
	@DisplayName("Test: buscar por solo apellido")
	void buscarPersonaPorApellido() {
		//Given
		List<Persona> listaPersonas = new ArrayList<Persona>();
		listaPersonas.add(DatosDummy.alumno01());
		listaPersonas.add(DatosDummy.alumno02());
		listaPersonas.add(DatosDummy.alumno03());
		
		Iterable<Persona> personas = alumnoRepository.saveAll(listaPersonas);
		
        //When
        String apellido = "Benitez";
        List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonaPorApellido(apellido);

        //Then
        assertThat(expected.size() == 2).isTrue();
	}

}
