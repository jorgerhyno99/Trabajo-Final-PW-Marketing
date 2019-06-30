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
@RequestMapping("/planDeMarketing")
@SessionAttributes({"planDeMarketing","campania"})
public class PlandeMarketingController {
	
	@Autowired
	private PlandemarketingBusiness planDeMarketingBusiness;
	
	@Autowired
	private CampaniaBusiness campaniaBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<PlanDeMarketing> planDeMarketings = planDeMarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/planDeMarketing/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			PlanDeMarketing planDeMarketing = new PlanDeMarketing();
			model.addAttribute("planDeMarketing", planDeMarketing);
		} catch (Exception e) {
			model.addAttribute("error", "PlanDeMarketing not saved");
		}
		return "/planDeMarketing/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("planDeMarketing") PlanDeMarketing planDeMarketing, Model model, SessionStatus status) {
		try {
			
			planDeMarketingBusiness.save(planDeMarketing);
			status.setComplete();
			model.addAttribute("success", "PlanDeMarketing guardado");
		} catch (Exception e) {
			model.addAttribute("error", "PlanDeMarketing no guardado");
		} 
		return "redirect:/planDeMarketing";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<PlanDeMarketing> buscado =  planDeMarketingBusiness.findById(id);
			List<PlanDeMarketing> planDeMarketings = new ArrayList<>();
			if (buscado.isPresent()) {
				planDeMarketings.add(buscado.get());				
			} 
			model.addAttribute("planDeMarketings", planDeMarketings);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/planDeMarketing/lista";		
	}
	
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<PlanDeMarketing> buscado = planDeMarketingBusiness.findById(id);
			if(buscado.isPresent()) {
				planDeMarketingBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "PlanDeMarketing not delete");
		}
		return "redirect:/planDeMarketing";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<PlanDeMarketing> buscado =  planDeMarketingBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("planDeMarketing", buscado.get());	
				
			} else {
				model.addAttribute("error", "Plan no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Plan no encontrada");
		}
		return "/planDeMarketing/edit";
	}
	
	@GetMapping("/{id}/campanias")
	public String viewCampanias( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<PlanDeMarketing> buscado = planDeMarketingBusiness.findById(id);
			if(buscado.isPresent()) {
				PlanDeMarketing planDeMarketing = buscado.get();
				List<Campania> campanias = planDeMarketing.fetchCampanias();
				model.addAttribute("planDeMarketing", planDeMarketing);
				model.addAttribute("campanias", campanias);
			}
		} catch (Exception e) {
			model.addAttribute("error", "PlanDeMarketing error");
		}
		return "/plandeMarketing/viewcampania";
	}
	
	@GetMapping("/campania/edit/{id}")
	public String plandeMarketing( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Campania> buscado =  campaniaBusiness.findById(id);
			
			if (buscado.isPresent()) {
				model.addAttribute("campania", buscado.get());
				
		
				
				List<PlanDeMarketing> departments = planDeMarketingBusiness.findAll();
				model.addAttribute("departments", departments);
				
			} else {
				model.addAttribute("error", "Campania no encontrado");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campania no encontrado");
		}
		return "/plandeMarketing/editcampania";
	}
	
	
	
	
	
	
	
	
}
