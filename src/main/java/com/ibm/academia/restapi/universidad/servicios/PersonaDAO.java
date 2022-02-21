package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.context.annotation.Primary;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface PersonaDAO extends GenericoDAO<Persona>
{
	public Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
	public Optional<Persona> buscarPorDni(String dni);
	public Iterable<Persona> buscarPersonaPorApellido(String apellido);
	public Persona actualizar(Long personaId, Persona persona);
}