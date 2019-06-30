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

import pe.edu.upc.model.entity.Control;
import pe.edu.upc.service.ControlBusiness;

@RestController
@RequestMapping("/controls")
public class ControlRestController {
	
	@Autowired
	private ControlBusiness controlService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Control> > fetchAll() {
		try {
			List<Control> controls = controlService.findAll();
			return new ResponseEntity< List<Control> >(controls, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<Control> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< Control > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<Control> control =  controlService.findById(id);
			if(control.isPresent()) {
				return new ResponseEntity< Control >(control.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< Control >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< Control >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid Control control) {
		try {
			controlService.save(control);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid Control control) {
		try {
			Optional<Control> buscado = controlService.findById(control.getId());
			if(buscado.isPresent()) {
				controlService.update(control);
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
			Optional<Control> control =  controlService.findById(id);
			if(control.isPresent()) {
				controlService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










