package pe.edu.upc.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.model.entity.AreaDeInteres;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.AreadeInteresBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/areadeinteres")
@SessionAttributes("areadeinteres")
public class AreaDeInteresController {
	
	@Autowired
	private AreadeInteresBusiness areadeinteresBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<AreaDeInteres> areadeinteress = areadeinteresBusiness.findAll();
			model.addAttribute("areadeinteress", areadeinteress);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/areadeinteres/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			AreaDeInteres areadeinteres = new AreaDeInteres();
			model.addAttribute("areadeinteres", areadeinteres);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "AreaDeInteres not saved");
		}
		return "/areadeinteres/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("areadeinteres") AreaDeInteres areadeinteres, Model model, SessionStatus status) {
		try {
			
			areadeinteresBusiness.save(areadeinteres);
			status.setComplete();
			model.addAttribute("success", "AreaDeInteres guardado");
		} catch (Exception e) {
			model.addAttribute("error", "AreaDeInteres no guardado");
		} 
		return "redirect:/areadeinteres";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<AreaDeInteres> buscado = areadeinteresBusiness.findById(id);
			if(buscado.isPresent()) {
				areadeinteresBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "AreaDeInteres not delete");
		}
		return "redirect:/areadeinteres";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<AreaDeInteres> buscado =  areadeinteresBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("areadeinteres", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/areadeinteres/edit";
	}
	
	
	
	
}
