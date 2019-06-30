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

import pe.edu.upc.model.entity.Estrategia;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.EstrategiaBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/estrategia")
@SessionAttributes("estrategia")
public class EstrategiaController {
	
	@Autowired
	private EstrategiaBusiness estrategiaBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Estrategia> estrategias = estrategiaBusiness.findAll();
			model.addAttribute("estrategias", estrategias);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/estrategia/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Estrategia estrategia = new Estrategia();
			model.addAttribute("estrategia", estrategia);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Estrategia not saved");
		}
		return "/estrategia/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("estrategia") Estrategia estrategia, Model model, SessionStatus status) {
		try {
			
			estrategiaBusiness.save(estrategia);
			status.setComplete();
			model.addAttribute("success", "Estrategia guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Estrategia no guardado");
		} 
		return "redirect:/estrategia";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Estrategia> buscado = estrategiaBusiness.findById(id);
			if(buscado.isPresent()) {
				estrategiaBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Estrategia not delete");
		}
		return "redirect:/estrategia";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Estrategia> buscado =  estrategiaBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("estrategia", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/estrategia/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Estrategia> buscado =  estrategiaBusiness.findById(id);
			List<Estrategia> estrategias = new ArrayList<>();
			if (buscado.isPresent()) {
				estrategias.add(buscado.get());				
			} 
			model.addAttribute("estrategias", estrategias);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/estrategia/lista";		
	}
	
	
}
