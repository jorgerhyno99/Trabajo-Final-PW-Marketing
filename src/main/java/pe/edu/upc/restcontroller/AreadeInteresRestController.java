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

import pe.edu.upc.model.entity.AreaDeInteres;
import pe.edu.upc.service.AreadeInteresBusiness;

@RestController
@RequestMapping("/areadeInteress")
public class AreadeInteresRestController {
	
	@Autowired
	private AreadeInteresBusiness areadeInteresService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<AreaDeInteres> > fetchAll() {
		try {
			List<AreaDeInteres> areadeInteress = areadeInteresService.findAll();
			return new ResponseEntity< List<AreaDeInteres> >(areadeInteress, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<AreaDeInteres> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< AreaDeInteres > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<AreaDeInteres> areadeInteres =  areadeInteresService.findById(id);
			if(areadeInteres.isPresent()) {
				return new ResponseEntity< AreaDeInteres >(areadeInteres.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< AreaDeInteres >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< AreaDeInteres >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid AreaDeInteres areadeInteres) {
		try {
			areadeInteresService.save(areadeInteres);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid AreaDeInteres areadeInteres) {
		try {
			Optional<AreaDeInteres> buscado = areadeInteresService.findById(areadeInteres.getId());
			if(buscado.isPresent()) {
				areadeInteresService.update(areadeInteres);
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
			Optional<AreaDeInteres> areadeInteres =  areadeInteresService.findById(id);
			if(areadeInteres.isPresent()) {
				areadeInteresService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










