package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

@RestController
@RequestMapping("/restapi")
public class ProfesorController {

	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO profesorDao;
	
	@PostMapping("/profesor")
	public ResponseEntity<?> crearProfesor(@RequestBody Persona profesor) {
		Persona profesorGuardado = profesorDao.guardar(profesor);
		return new ResponseEntity<Persona> (profesorGuardado, HttpStatus.CREATED);
	}
	
	@GetMapping("/profesores/lista")
	public ResponseEntity<?> obtenerTodosProfesores()
	{
		List<Persona> profesores = (List<Persona>) profesorDao.buscarTodos();
		
		if(profesores.isEmpty())
			throw new NotFoundException("No existen profesores");
		
		return new ResponseEntity<List<Persona>>(profesores, HttpStatus.OK);
	}
	
	@GetMapping("/profesor/{profesorId}")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Long profesorId)
    {
        Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
        
        if(!oProfesor.isPresent()) 
            throw new NotFoundException(String.format("Profesor con id %d no existe", profesorId));
        
        return new ResponseEntity<Persona>(oProfesor.get(), HttpStatus.OK);
    }
	
	@DeleteMapping("/profesor/eliminar/profesorId/{profesorId}")
	public ResponseEntity<?> eliminarProfesor(@PathVariable Long profesorId)
	{
		Optional<Persona> oProfesor = profesorDao.buscarPorId(profesorId);
		
		if(!oProfesor.isPresent())
			throw new NotFoundException(String.format("El Profesor con ID %d no existe", profesorId));
		
		profesorDao.eliminarPorId(oProfesor.get().getId()); 
		return new ResponseEntity<String>("Profesor ID: " + profesorId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/profesor/actualizar/profesorId/{profesorId}")
	public ResponseEntity<?> actualizarProfesor(@PathVariable Long profesorId, @RequestBody Persona profesor, BindingResult result)
	{
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors())
		{
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Persona profesorActualizado = ((ProfesorDAO)profesorDao).actualizar(profesorId, profesor);
		return new ResponseEntity<Persona>(profesorActualizado, HttpStatus.OK);
	}
	
	@GetMapping("/profesor/carrera/{carrera}")
    public ResponseEntity<?> findProfesoresByCarrera(@PathVariable String carrera)
    {
		Iterable<Persona> profesores = ((ProfesorDAO)profesorDao).findProfesoresByCarrera(carrera);
		
		if(!profesores.iterator().hasNext())
			throw new NotFoundException(String.format("La carrera %s no tiene profesores", carrera));
		
		return new ResponseEntity<Iterable<Persona>> (profesores, HttpStatus.OK);	
		
    }
	
}
