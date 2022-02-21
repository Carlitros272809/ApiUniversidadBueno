package com.ibm.academia.restapi.universidad.datos;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

public class DatosDummy {
	
	//CARRERAS
	public static Carrera carrera01() 
	{
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5, "CRuiz"); 
	}

	public static Carrera carrera02() 
	{
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4, "CRuiz");
	}

	public static Carrera carrera03() 
	{
		return new Carrera(null, "Ingenieria Industrial", 60, 5, "CRuiz");
	}
	
	//ALUMNOS
	public static Persona alumno01()
	{
		return new Alumno(null, "Jhon", "Benitez", "4223715", "CRuiz", new Direccion());
	}
	
	public static Persona alumno02() 
	{
		return new Alumno(null, "Andres", "Benitez", "45233891", "CRuiz", new Direccion());
	}

	public static Persona alumno03() 
	{
		return new Alumno(null, "Joaquin", "Leon", "45233012", "CRuiz", new Direccion());
	}
	
	//EMPLEADOS
	public static Persona empleado01() 
	{
		return new Empleado(null, "Lautaro", "Lopez", "25147036", "CRuiz", new Direccion(), new BigDecimal("46750"), TipoEmpleado.ADMINISTRATIVO);
	}
	
	public static Persona empleado02() 
	{
		return new Empleado(null, "Lenadro", "Lopez", "25174630", "CRuiz", new Direccion(), new BigDecimal("46750.70"), TipoEmpleado.MANTENIMIENTO);
	}
	
	//PROFESOR
	public static Persona profesor01()
	{
		return new Profesor(null, "Martin", "Axacar", "4477899", "CRuiz", new Direccion(), new BigDecimal("600000"));

	}
	
	//DIRECCION
	public static Direccion direccion01() {
		return new Direccion("Avenida Oro","4","55030","101","1","Mexico");
	}
	
	public static Direccion direccion02() {
		return new Direccion("Avenida Texcoco","4","55089","102","7","Mexico"); 
	}
	
	//PABELLON
	public static Pabellon pabellon01() {
		return new Pabellon(null,(double) 60,"Prisoners",direccion01(),"DCastillo");
	}
	
	public static Pabellon pabellon02() {
		return new Pabellon(null,(double) 70,"Goat",direccion02(),"CRuiz");
	}
	
	//AULAS
	public static Aula aula01() {
		return new Aula(null,101,"10x15",30,TipoPizarron.PIZARRON_BLANCO,"RaCastillo");
	}
	
	public static Aula aula02() {
		return new Aula(null,102,"15x10",40,TipoPizarron.PIZARRON_TIZA,"DCastillo");
	}
	
}
