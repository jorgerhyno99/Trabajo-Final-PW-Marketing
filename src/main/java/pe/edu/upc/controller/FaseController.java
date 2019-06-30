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

import pe.edu.upc.model.entity.Fase;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.FaseBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/fase")
@SessionAttributes("fase")
public class FaseController {
	
	@Autowired
	private FaseBusiness faseBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Fase> fases = faseBusiness.findAll();
			model.addAttribute("fases", fases);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/fase/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Fase fase = new Fase();
			model.addAttribute("fase", fase);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Fase not saved");
		}
		return "/fase/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("fase") Fase fase, Model model, SessionStatus status) {
		try {
			
			faseBusiness.save(fase);
			status.setComplete();
			model.addAttribute("success", "Fase guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Fase no guardado");
		} 
		return "redirect:/fase";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Fase> buscado = faseBusiness.findById(id);
			if(buscado.isPresent()) {
				faseBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Fase not delete");
		}
		return "redirect:/fase";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Fase> buscado =  faseBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("fase", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/fase/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Fase> buscado =  faseBusiness.findById(id);
			List<Fase> fases = new ArrayList<>();
			if (buscado.isPresent()) {
				fases.add(buscado.get());				
			} 
			model.addAttribute("fases", fases);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/fase/lista";		
	}
	
	
}
