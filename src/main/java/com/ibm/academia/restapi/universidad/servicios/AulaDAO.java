package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula> {
	
	public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron);
	
	public Iterable<Aula> findAulasByPabellonNombre(String nombre);
	
	public Iterable<Aula> findAulasByNumeroAula(Integer numeroAula);
	
	public Aula actualizar(Long aulaId, Aula aula);
}