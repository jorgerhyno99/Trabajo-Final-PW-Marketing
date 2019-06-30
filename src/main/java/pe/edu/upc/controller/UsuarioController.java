package pe.edu.upc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.model.entity.Usuario;
import pe.edu.upc.service.PlandemarketingBusiness;
import pe.edu.upc.service.RolBusiness;
import pe.edu.upc.service.UsuarioBusiness;

@Controller
@RequestMapping("/usuario")
@SessionAttributes("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	@Autowired
	private RolBusiness rolBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Usuario> usuarios = usuarioBusiness.findAll();
			model.addAttribute("usuarios", usuarios);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/usuario/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Usuario usuario = new Usuario();
			model.addAttribute("usuario", usuario);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			model.addAttribute("listaRols", rolBusiness.findAll());
					
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Usuario not saved");
		}
		return "/usuario/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, Model model, SessionStatus status) {
		try {
			
			usuarioBusiness.save(usuario);
			status.setComplete();
			model.addAttribute("success", "Usuario guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Usuario no guardado");
		} 
		return "redirect:/usuario";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Usuario> buscado = usuarioBusiness.findById(id);
			if(buscado.isPresent()) {
				usuarioBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Usuario not delete");
		}
		return "redirect:/usuario";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Usuario> buscado =  usuarioBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("usuario", buscado.get());	
				
				model.addAttribute("listaRols", rolBusiness.findAll());
				
				
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/usuario/edit";
	}
	
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Usuario> buscado =  usuarioBusiness.findById(id);
			List<Usuario> usuarios = new ArrayList<>();
			if (buscado.isPresent()) {
				usuarios.add(buscado.get());				
			} 
			model.addAttribute("usuarios", usuarios);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/usuario/lista";		
	}
	
}
