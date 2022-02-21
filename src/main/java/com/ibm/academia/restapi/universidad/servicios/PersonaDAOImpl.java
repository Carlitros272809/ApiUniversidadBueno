package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;

@Service
public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO
{	
	public PersonaDAOImpl(PersonaRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido) 
	{
		return repository.buscarPorNombreYApellido(nombre, apellido);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarPorDni(String dni) 
	{
		return repository.buscarPorDni(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarPersonaPorApellido(String apellido) 
	{
		return repository.buscarPersonaPorApellido(apellido);
	}
	
	@Override
	@Transactional
	public Persona actualizar(Long personaId, Persona persona) 
	{
		Optional<Persona> oPersona = repository.findById(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("La persona con ID %d no existe", personaId));
		
		Persona personaActualizado = null;
		oPersona.get().setNombre(persona.getNombre());
		oPersona.get().setApellido(persona.getApellido());
		oPersona.get().setDireccion(persona.getDireccion());
		personaActualizado = repository.save(oPersona.get());
		
		return personaActualizado;
	}
}