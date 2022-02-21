package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

@SpringBootTest
public class AulaDAOImplTest {
	@MockBean
	private AulaRepository aulaRepository;
	
	@Autowired 
	private AulaDAO aulaDao;

	@Test
	@Disabled
	@DisplayName("Test: buscar aula por tipo Pizarron ")
	void findAulasByTipoPizarron() {
		
		//When
		aulaDao.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
		
		//Then
		verify(aulaRepository).findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
	
	}
	
	@Test
	//@Disabled
	@DisplayName("Test: Buscar aula por nombre pabellon")
	void findAulasByPabellonNombre() {
		
		//When
		aulaDao.findAulasByPabellonNombre(anyString());
		
		//Then
		verify(aulaRepository).findAulasByPabellonNombre(anyString());
		
	}
	
	@Test
	@Disabled
	@DisplayName("Test: buscar aula por numero de aula")
	void findAulasByNumeroAula() {
		
		//Given
		int numero = (int) (Math.random() * 2000 + 1);
		
		//When
		aulaDao.findAulasByNumeroAula(numero);
		
		//Then
		verify(aulaRepository).findAulasByNumeroAula(numero);
		
	}
	
}
