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

import pe.edu.upc.model.entity.Campania;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.CampaniaBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/campania")
@SessionAttributes("campania")
public class CampaniaController {
	
	@Autowired
	private CampaniaBusiness campaniaBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Campania> campanias = campaniaBusiness.findAll();
			model.addAttribute("campanias", campanias);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/campania/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Campania campania = new Campania();
			model.addAttribute("campania", campania);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("listplanDeMarketings", planDeMarketings);
		
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Campania not saved");
		}
		return "/campania/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("campania") Campania campania, Model model, SessionStatus status) {
		try {
			
			campaniaBusiness.save(campania);
			status.setComplete();
			model.addAttribute("success", "Campania guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Campania no guardado");
		} 
		return "redirect:/campania";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Campania> buscado = campaniaBusiness.findById(id);
			if(buscado.isPresent()) {
				campaniaBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campania not delete");
		}
		return "redirect:/campania";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Campania> buscado =  campaniaBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("campania", buscado.get());	
							
				List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
				model.addAttribute("listplanDeMarketings", planDeMarketings);
			
				
				
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/campania/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Campania> buscado =  campaniaBusiness.findById(id);
			List<Campania> campanias = new ArrayList<>();
			if (buscado.isPresent()) {
				campanias.add(buscado.get());				
			} 
			model.addAttribute("campanias", campanias);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/campania/lista";		
	}
	
	
}
