package pe.edu.upc.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.entity.Prioridad;
import pe.edu.upc.service.PrioridadBusiness;

@RestController
@RequestMapping("/prioridads")
public class PrioridadRestController {
	
	@Autowired
	private PrioridadBusiness prioridadService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Prioridad> > fetchAll() {
		try {
			List<Prioridad> prioridads = prioridadService.findAll();
			return new ResponseEntity< List<Prioridad> >(prioridads, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<Prioridad> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< Prioridad > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<Prioridad> prioridad =  prioridadService.findById(id);
			if(prioridad.isPresent()) {
				return new ResponseEntity< Prioridad >(prioridad.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< Prioridad >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< Prioridad >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid Prioridad prioridad) {
		try {
			prioridadService.save(prioridad);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid Prioridad prioridad) {
		try {
			Optional<Prioridad> buscado = prioridadService.findById(prioridad.getId());
			if(buscado.isPresent()) {
				prioridadService.update(prioridad);
				return new ResponseEntity< Object >(HttpStatus.OK);
			} else {
				return new ResponseEntity< Object >(HttpStatus.NOT_FOUND);
			}		
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping( path = "/{id}", produces = "text/plain" )
	public ResponseEntity< String > deleteById(@PathVariable("id") Integer id) {
		try {
			Optional<Prioridad> prioridad =  prioridadService.findById(id);
			if(prioridad.isPresent()) {
				prioridadService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










