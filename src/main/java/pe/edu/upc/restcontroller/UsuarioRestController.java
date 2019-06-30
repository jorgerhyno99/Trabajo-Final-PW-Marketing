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

import pe.edu.upc.model.entity.Usuario;
import pe.edu.upc.service.UsuarioBusiness;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioBusiness usuarioService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Usuario> > fetchAll() {
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			return new ResponseEntity< List<Usuario> >(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< List<Usuario> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity< Usuario > fetchById(@PathVariable("id") Integer id) {
		try {
			Optional<Usuario> usuario =  usuarioService.findById(id);
			if(usuario.isPresent()) {
				return new ResponseEntity< Usuario >(usuario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity< Usuario >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< Usuario >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody @Valid Usuario usuario) {
		try {
			usuarioService.save(usuario);
			return new ResponseEntity< Object >(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity< Object >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@RequestBody @Valid Usuario usuario) {
		try {
			Optional<Usuario> buscado = usuarioService.findById(usuario.getId());
			if(buscado.isPresent()) {
				usuarioService.update(usuario);
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
			Optional<Usuario> usuario =  usuarioService.findById(id);
			if(usuario.isPresent()) {
				usuarioService.deleteById(id);
				return new ResponseEntity< String >("Delete OK", HttpStatus.OK);
			} else {
				return new ResponseEntity< String >(HttpStatus.NOT_FOUND);
			}			
		} catch (Exception e) {
			return new ResponseEntity< String >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}










