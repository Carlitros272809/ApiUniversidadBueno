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
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class AulaRepositoryTest {

	@Autowired
	@Qualifier("repositorioPabellon")
	private PabellonRepository pabellonRepository;

	@Autowired
	@Qualifier("repositorioAula")
	private AulaRepository aulaRepository;

	@BeforeEach
	void setUp() {

		// Given
		Aula aula1 = aulaRepository.save(DatosDummy.aula01());
		Aula aula2 = aulaRepository.save(DatosDummy.aula02());

		Pabellon pabellon1 = pabellonRepository.save(DatosDummy.pabellon01());
		Pabellon pabellon2 = pabellonRepository.save(DatosDummy.pabellon02());

		aula1.setPabellon(pabellon1);
		aula2.setPabellon(pabellon1);

	}

	@AfterEach
	void tearDown() {
		aulaRepository.deleteAll();
	}

	@Test
	@Disabled
	@DisplayName("Test: buscar aula por tipo Pizarron ")
	void findAulasByTipoPizarron() {

		// When
		List<Aula> expected = (List<Aula>) ((AulaRepository) aulaRepository)
				.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);

		// Then
		assertThat(expected.size() == 1).isTrue();

	}

	@Test
	// @Disabled
	@DisplayName("Test: Buscar aula por nombre pabellon")
	void findAulasByPabellonNombre() {

		// When
		String nombrePabellon = "Prisoners";
		List<Aula> expected = (List<Aula>) ((AulaRepository) aulaRepository).findAulasByPabellonNombre(nombrePabellon);

		// Then
		assertThat(expected.size() == 2).isTrue();

	}

	@Test
	@Disabled
	@DisplayName("Test: buscar aula por numero de aula")
	void findAulasByNumeroAula() {

		// When
		Integer numeroAula = 101;
		List<Aula> expected = (List<Aula>) ((AulaRepository) aulaRepository).findAulasByNumeroAula(numeroAula);

		// Then
		assertThat(expected.size() == 1).isTrue();

	}

}
