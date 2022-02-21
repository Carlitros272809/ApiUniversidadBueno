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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

@RestController
@RequestMapping("/restapi")
public class PabellonesController {
	
	private final static Logger logger = LoggerFactory.getLogger(AulaController.class);

	@Autowired
	@Qualifier("pabellonDAOImpl")
	private PabellonDAO pabellonDao;
	
	@GetMapping("/pabellon/pabellonId/{pabellonId}")
	public ResponseEntity<?> obtenerPabellonPorId(@PathVariable Long pabellonId){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El Pabellon con id %d no existe", pabellonId));
		
		return new ResponseEntity<Pabellon> (oPabellon.get(), HttpStatus.OK);
		
	}
	
	@PostMapping("/pabellon")
	public ResponseEntity<?> crearPabellon(@RequestBody Pabellon pabellon){
		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}
	
	@GetMapping("/pabellones/lista")
	public List<Pabellon> listarPabellones() {
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		return pabellones;
	}
	
	@DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminarPabellon(@PathVariable Long pabellonId){
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El Pabellon con id %d no existe", pabellonId));
		
		pabellonDao.eliminarPorId(pabellonId);
		return new ResponseEntity<>("El Pabellon con id: " + pabellonId + " fue eliminada", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizarPabellon(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon, BindingResult result)
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
		
		Pabellon pabellonActualizada = null;
		
		try
		{
			pabellonActualizada = pabellonDao.actualizar(pabellonId, pabellon);
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
			throw e;
		}
		
		return new ResponseEntity<Pabellon>(pabellonActualizada, HttpStatus.OK);
	}
	
	@GetMapping("/pabellon/pabellonLocalidad/{pabellonLocalidad}")
	public ResponseEntity<?> obtenerPabellonPorId(@PathVariable String pabellonLocalidad){
		
		Iterable<Pabellon> oPabellon = (Iterable<Pabellon>) pabellonDao.findPabellonesByDireccionLocalidad(pabellonLocalidad);
		
		if(!oPabellon.iterator().hasNext())
			throw new NotFoundException(String.format("El Pabellon con la localidad %s no existe", pabellonLocalidad));
		
		return new ResponseEntity<Iterable<Pabellon>> (oPabellon, HttpStatus.OK);
		
	}
	
	@GetMapping("/pabellon/pabellonNombre/{pabellonNombre}")
	public ResponseEntity<?> findPabellonByNombreIgnoreCase(@PathVariable String pabellonNombre){
		
		Iterable<Pabellon> oPabellon = (Iterable<Pabellon>) pabellonDao.findPabellonesByNombre(pabellonNombre);
		
		if(!oPabellon.iterator().hasNext())
			throw new NotFoundException(String.format("El Pabellon con nombre %s no existe", pabellonNombre));
		
		return new ResponseEntity<Iterable<Pabellon>> (oPabellon, HttpStatus.OK);
		
	}
}
