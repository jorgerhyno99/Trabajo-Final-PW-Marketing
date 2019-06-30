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

import pe.edu.upc.model.entity.Objetivo;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.ObjetivoBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/objetivo")
@SessionAttributes("objetivo")
public class ObjetivoController {
	
	@Autowired
	private ObjetivoBusiness objetivoBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Objetivo> objetivos = objetivoBusiness.findAll();
			model.addAttribute("objetivos", objetivos);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/objetivo/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Objetivo objetivo = new Objetivo();
			model.addAttribute("objetivo", objetivo);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Objetivo not saved");
		}
		return "/objetivo/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("objetivo") Objetivo objetivo, Model model, SessionStatus status) {
		try {
			
			objetivoBusiness.save(objetivo);
			status.setComplete();
			model.addAttribute("success", "Objetivo guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Objetivo no guardado");
		} 
		return "redirect:/objetivo";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Objetivo> buscado = objetivoBusiness.findById(id);
			if(buscado.isPresent()) {
				objetivoBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Objetivo not delete");
		}
		return "redirect:/objetivo";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Objetivo> buscado =  objetivoBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("objetivo", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/objetivo/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Objetivo> buscado =  objetivoBusiness.findById(id);
			List<Objetivo> objetivos = new ArrayList<>();
			if (buscado.isPresent()) {
				objetivos.add(buscado.get());				
			} 
			model.addAttribute("objetivos", objetivos);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/objetivo/lista";		
	}
	
	
}
