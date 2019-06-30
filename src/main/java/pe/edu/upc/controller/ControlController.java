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

import pe.edu.upc.model.entity.Control;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.ControlBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/control")
@SessionAttributes("control")
public class ControlController {
	
	@Autowired
	private ControlBusiness controlBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Control> controls = controlBusiness.findAll();
			model.addAttribute("controls", controls);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/control/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Control control = new Control();
			model.addAttribute("control", control);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Control not saved");
		}
		return "/control/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("control") Control control, Model model, SessionStatus status) {
		try {
			
			controlBusiness.save(control);
			status.setComplete();
			model.addAttribute("success", "Control guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Control no guardado");
		} 
		return "redirect:/control";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Control> buscado = controlBusiness.findById(id);
			if(buscado.isPresent()) {
				controlBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Control not delete");
		}
		return "redirect:/control";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Control> buscado =  controlBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("control", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/control/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Control> buscado =  controlBusiness.findById(id);
			List<Control> controls = new ArrayList<>();
			if (buscado.isPresent()) {
				controls.add(buscado.get());				
			} 
			model.addAttribute("controls", controls);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/control/lista";		
	}
	
	
}
