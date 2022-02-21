package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO{

	@Autowired
	public AulaDAOImpl(@Qualifier("repositorioAula")AulaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron) {
		return ((AulaRepository)repository).findAulasByTipoPizarron(tipoPizarron);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Aula> findAulasByNumeroAula(Integer numeroAula) {
		return ((AulaRepository)repository).findAulasByNumeroAula(numeroAula);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Aula> findAulasByPabellonNombre(String nombre) {
		return ((AulaRepository)repository).findAulasByPabellonNombre(nombre);
	}

	@Override
	@Transactional
	public Aula actualizar(Long aulaId, Aula aula) {
		Optional <Aula> oAula = repository.findById(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El Aula con id %d no existe", aulaId));
		
		Aula aulaActualizada = null;
		oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
		oAula.get().setMedidas(aula.getMedidas());
		oAula.get().setNumeroAula(aula.getNumeroAula());
		oAula.get().setTipoPizarron(aula.getTipoPizarron());
		
		return aulaActualizada;
	}

}
