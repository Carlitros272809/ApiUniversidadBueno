package com.ibm.academia.restapi.universidad.servicios;

import javax.validation.Valid;

import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon>{
	
	public Iterable <Pabellon> findPabellonesByDireccionLocalidad(String localidad);
	public Iterable <Pabellon> findPabellonesByNombre(String nombre);
	public Pabellon actualizar(Long pabellonId, Pabellon pabellon);
	
}