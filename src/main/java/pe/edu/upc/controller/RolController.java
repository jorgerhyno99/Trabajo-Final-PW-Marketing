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
import pe.edu.upc.model.entity.Rol;
import pe.edu.upc.model.entity.Usuario;
import pe.edu.upc.service.PlandemarketingBusiness;
import pe.edu.upc.service.RolBusiness;
import pe.edu.upc.service.UsuarioBusiness;

@Controller
@RequestMapping("/rol")
@SessionAttributes({ "rol", "usuario" })
public class RolController {

	@Autowired
	private RolBusiness rolBusiness;

	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@GetMapping
	public String listado(Model model) {
		try {
			List<Rol> rols = rolBusiness.findAll();
			model.addAttribute("rols", rols);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/rol/lista";
	}

	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Rol rol = new Rol();
			model.addAttribute("rol", rol);

			List<PlanDeMarketing> rols = plandemarketingBusiness.findAll();
			model.addAttribute("rols", rols);

		} catch (Exception e) {
			model.addAttribute("error", "Rol not saved");
		}
		return "/rol/new";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("rol") Rol rol, Model model, SessionStatus status) {
		try {

			rolBusiness.save(rol);
			status.setComplete();
			model.addAttribute("success", "Rol guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Rol no guardado");
		}
		return "redirect:/rol";
	}

	@GetMapping("/del/{id}")
	public String del(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Rol> buscado = rolBusiness.findById(id);
			if (buscado.isPresent()) {
				rolBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Rol not delete");
		}
		return "redirect:/rol";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Rol> buscado = rolBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("rol", buscado.get());
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/rol/edit";
	}

	@GetMapping("/{id}/usuarios")
	public String viewRoles(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Rol> buscado = rolBusiness.findById(id);
			if (buscado.isPresent()) {
				Rol rol = buscado.get();
				List<Usuario> usuarios = rol.fetchUsuarios();
				model.addAttribute("rol", rol);
				model.addAttribute("usuarios", usuarios);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Rol error");
		}
		return "/rol/viewusuario";
	}

	@GetMapping("/campania/edit/{id}")
	public String plandeMarketing(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<Usuario> buscado = usuarioBusiness.findById(id);

			if (buscado.isPresent()) {

			} else {
				model.addAttribute("error", "Campania no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campania no encontrado");
		}
		return "/rol/editusuario";
	}

	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Rol> buscado =  rolBusiness.findById(id);
			List<Rol> rols = new ArrayList<>();
			if (buscado.isPresent()) {
				rols.add(buscado.get());				
			} 
			model.addAttribute("rols", rols);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/rol/lista";		
	}
	
	
}
