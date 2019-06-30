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

import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.PlandemarketingBusiness;

@RestController
@RequestMapping("/plandemarketings")
public class PlandemarketingRestController {
	
	@Autowired
	private PlandemarketingBusiness plandemarketingService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<PlanDeMarketing> > fetchAll() {
		try {
			List<PlanDeMarketing> plandemarketings = plandemarketingService.findAll();
			return new ResponseEntity< List<PlanDeMarketing> >(plandemarketings, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<PlanDeMarketing> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< PlanDeMarketing > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<PlanDeMarketing> plandemarketing =  plandemarketingService.findById(id);
			if(plandemarketing.isPresent()) {
				return new ResponseEntity< PlanDeMarketing >(plandemarketing.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< PlanDeMarketing >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< PlanDeMarketing >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid PlanDeMarketing plandemarketing) {
		try {
			plandemarketingService.save(plandemarketing);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid PlanDeMarketing plandemarketing) {
		try {
			Optional<PlanDeMarketing> buscado = plandemarketingService.findById(plandemarketing.getPlanDeMarketing_id());
			if(buscado.isPresent()) {
				plandemarketingService.update(plandemarketing);
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
			Optional<PlanDeMarketing> plandemarketing =  plandemarketingService.findById(id);
			if(plandemarketing.isPresent()) {
				plandemarketingService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










