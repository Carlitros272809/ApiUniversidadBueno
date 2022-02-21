package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

@RestController
@RequestMapping("/restapi")
public class AulaController {
	
	private final static Logger logger = LoggerFactory.getLogger(AulaController.class);

	@Autowired
	@Qualifier("aulaDAOImpl")
	private AulaDAO aulaDao;
	
	@PostMapping("/aula")
	public ResponseEntity<?> crearAaula(@RequestBody Aula aula){
		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}
	
	@GetMapping("/aula/aulaId/{aulaId}")
	public ResponseEntity<?> obtenerAulaPorId(@PathVariable Long aulaId){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El Aula con id %d no existe", aulaId));
		
		return new ResponseEntity<Aula> (oAula.get(), HttpStatus.OK);
		
	}
	
	@GetMapping("/aulas/lista")
	public List<Aula> listarAulas()
	{
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();
		return aulas;
	}
	
	@DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
	public ResponseEntity<?> eliminarAula(@PathVariable Long aulaId){
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);
		
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El Aula con id %d no existe", aulaId));
		
		aulaDao.eliminarPorId(aulaId);
		return new ResponseEntity<>("El Aula con id: " + aulaId + " fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/aula/actualizar/aulaId/{aulaId}")
	public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula, BindingResult result)
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
		
		Aula aulaActualizada = null;
		
		try
		{
			aulaActualizada = aulaDao.actualizar(aulaId, aula);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	@GetMapping("/aula/pizarron/")
	public ResponseEntity<?> obtenerAulaPorTipoPizarron(@RequestParam(value = "tipoPizarron") TipoPizarron tipoPizarron){
		Iterable<Aula> oAula = (Iterable<Aula>) aulaDao.findAulasByTipoPizarron(tipoPizarron);
		
		if(!oAula.iterator().hasNext())
			throw new NotFoundException(String.format("El Aula con ese Tipo de Pizarron no existe"));
		
		return new ResponseEntity<Iterable<Aula>> (oAula, HttpStatus.OK);
		
	}
	
	@GetMapping("/aula/numeroAula/")
	public ResponseEntity<?> obtenerAulaPorTipoPizarron(@RequestParam(value = "numeroAula") Integer numeroAula){
		Iterable<Aula> oAula = (Iterable<Aula>) aulaDao.findAulasByNumeroAula(numeroAula);
		
		if(!oAula.iterator().hasNext())
			throw new NotFoundException(String.format("El Aula con numero de Aula %i no existe", numeroAula));
		
		return new ResponseEntity<Iterable<Aula>> (oAula, HttpStatus.OK);
		
	}
	
	@GetMapping("/aula/pabellonNombre/")
	public ResponseEntity<?> obtenerAulaPorPabellonNombre(@RequestParam(value = "pabellonNombre") String pabellonNombre){
		Iterable<Aula> oAula = (Iterable<Aula>) aulaDao.findAulasByPabellonNombre(pabellonNombre);
		
		if(!oAula.iterator().hasNext())
			throw new NotFoundException(String.format("El Aula con nombre de Pabellon %s no existe", pabellonNombre));
		
		return new ResponseEntity<Iterable<Aula>> (oAula, HttpStatus.OK);
		
	}
}
