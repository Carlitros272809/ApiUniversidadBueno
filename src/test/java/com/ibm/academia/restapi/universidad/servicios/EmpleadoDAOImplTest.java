package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;

@SpringBootTest
public class EmpleadoDAOImplTest {
	
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Test
	@DisplayName("Test: buscar empleado por tipo empleado")
	void findEmpleadoByTipoEmpleado() {
		//When
		empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		
		//Then 
		verify(empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
	}
	
}
