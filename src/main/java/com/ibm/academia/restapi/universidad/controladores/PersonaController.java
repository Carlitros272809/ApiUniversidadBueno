package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

@RestController
@RequestMapping("/restapi")
public class PersonaController {

	@Autowired
	@Qualifier("personaDAOImpl")
	private PersonaDAO personaDao;
	
	@PostMapping("/persona")
	public ResponseEntity<?> crearPersona(@RequestBody Persona persona) {

		Persona personaGuardada = personaDao.guardar(persona);
		return new ResponseEntity<Persona>(personaGuardada, HttpStatus.CREATED);
	}
	
	@GetMapping("/persona/{personaId}")
    public ResponseEntity<?> obtenerPersonaPorId(@PathVariable Long personaId)
    {
        Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
        
       if(!oPersona.isPresent()) 
            throw new NotFoundException(String.format("Persona con id %d no existe", personaId));
        
        return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
    }
	
	@GetMapping("/personas/lista")
	public ResponseEntity<?> obtenerTodos()
	{
		List<Persona> personas = (List<Persona>) personaDao.buscarTodos();
		
		if(personas.isEmpty())
			throw new NotFoundException("No existen personas");
		
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@DeleteMapping("/persona/eliminar/personaId/{personaId}")
	public ResponseEntity<?> eliminarPersona(@PathVariable Long personaId)
	{
		Optional<Persona> oPersona = personaDao.buscarPorId(personaId);
		
		if(!oPersona.isPresent())
			throw new NotFoundException(String.format("La persona con ID %d no existe", personaId));
		
		personaDao.eliminarPorId(oPersona.get().getId()); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PutMapping("/persona/actualizar/personaId/{personaId}")
	public ResponseEntity<?> actualizarPersona(@PathVariable Long personaId,@RequestBody Persona persona){
		
		Persona personaActualizado = personaDao.actualizar(personaId, persona);
		return new ResponseEntity<Persona>(personaActualizado, HttpStatus.OK);
		
	}
	
	@GetMapping("/persona/apellido/{apellidoPersona}")
	public ResponseEntity<?> buscarAlumnoPorApellido(@PathVariable String apellidoPersona) {
		
		Iterable<Persona> oPersona = (Iterable<Persona>) personaDao.buscarPersonaPorApellido(apellidoPersona);

		if(!oPersona.iterator().hasNext())
            throw new NotFoundException(String.format("Persona con apellido %s no existe", apellidoPersona));
   
		
		return new ResponseEntity<Iterable<Persona>>(oPersona, HttpStatus.OK);
	}
	
	@GetMapping("/persona/dni/{personaDni}")
	public ResponseEntity<?> buscarPersonaPorDni(@PathVariable String personaDni) {
		
		Optional<Persona> oPersona = (Optional<Persona>) personaDao.buscarPorDni(personaDni);
		
		if(!oPersona.isPresent()) 
            throw new NotFoundException(String.format("Persona con dni %s no existe", personaDni));
   
		
		return new ResponseEntity<Persona>(oPersona.get(), HttpStatus.OK);
	}
}
