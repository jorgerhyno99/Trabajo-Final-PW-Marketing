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

import pe.edu.upc.model.entity.AccionCorrectora;
import pe.edu.upc.service.AccioncorrectoraBusiness;

@RestController
@RequestMapping("/accioncorrectoras")
public class AccioncorrectoraRestController {
	
	@Autowired
	private AccioncorrectoraBusiness accioncorrectoraService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<AccionCorrectora> > fetchAll() {
		try {
			List<AccionCorrectora> accioncorrectoras = accioncorrectoraService.findAll();
			return new ResponseEntity< List<AccionCorrectora> >(accioncorrectoras, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<AccionCorrectora> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< AccionCorrectora > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<AccionCorrectora> accioncorrectora =  accioncorrectoraService.findById(id);
			if(accioncorrectora.isPresent()) {
				return new ResponseEntity< AccionCorrectora >(accioncorrectora.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< AccionCorrectora >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< AccionCorrectora >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid AccionCorrectora accioncorrectora) {
		try {
			accioncorrectoraService.save(accioncorrectora);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid AccionCorrectora accioncorrectora) {
		try {
			Optional<AccionCorrectora> buscado = accioncorrectoraService.findById(accioncorrectora.getId());
			if(buscado.isPresent()) {
				accioncorrectoraService.update(accioncorrectora);
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
			Optional<AccionCorrectora> accioncorrectora =  accioncorrectoraService.findById(id);
			if(accioncorrectora.isPresent()) {
				accioncorrectoraService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










