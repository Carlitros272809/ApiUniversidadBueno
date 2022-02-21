package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {

	@Autowired
	public PabellonDAOImpl(@Qualifier("repositorioPabellon")PabellonRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Pabellon> findPabellonesByDireccionLocalidad(String localidad) {
		return repository.findPabellonesByDireccionLocalidad(localidad);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Pabellon> findPabellonesByNombre(String nombre) {
		return repository.findPabellonesByNombre(nombre);
	}

	@Override
	@Transactional
	public Pabellon actualizar(Long pabellonId, Pabellon pabellon) {
		Optional <Pabellon> oPabellon = repository.findById(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El Pabellon con id %d no existe", pabellonId));
		
		Pabellon pabellonActualizado = null;
		oPabellon.get().setMetrosCuadrados(pabellon.getMetrosCuadrados());
		oPabellon.get().setNombre(pabellon.getNombre());
		
		return pabellonActualizado;
	}
	
}