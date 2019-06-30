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
import pe.edu.upc.model.entity.Prioridad;
import pe.edu.upc.service.PlandemarketingBusiness;
import pe.edu.upc.service.PrioridadBusiness;

@Controller
@RequestMapping("/prioridad")
@SessionAttributes("prioridad")
public class PrioridadController {
	
	@Autowired
	private PrioridadBusiness prioridadBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Prioridad> prioridads = prioridadBusiness.findAll();
			model.addAttribute("prioridads", prioridads);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/prioridad/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Prioridad prioridad = new Prioridad();
			model.addAttribute("prioridad", prioridad);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Prioridad not saved");
		}
		return "/prioridad/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("prioridad") Prioridad prioridad, Model model, SessionStatus status) {
		try {
			
			prioridadBusiness.save(prioridad);
			status.setComplete();
			model.addAttribute("success", "Prioridad guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Prioridad no guardado");
		} 
		return "redirect:/prioridad";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Prioridad> buscado = prioridadBusiness.findById(id);
			if(buscado.isPresent()) {
				prioridadBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Prioridad not delete");
		}
		return "redirect:/prioridad";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Prioridad> buscado =  prioridadBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("prioridad", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/prioridad/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Prioridad> buscado =  prioridadBusiness.findById(id);
			List<Prioridad> prioridads = new ArrayList<>();
			if (buscado.isPresent()) {
				prioridads.add(buscado.get());				
			} 
			model.addAttribute("prioridads", prioridads);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/prioridad/lista";		
	}
	
	
}
