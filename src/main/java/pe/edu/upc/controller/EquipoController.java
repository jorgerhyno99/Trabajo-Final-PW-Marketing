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

import pe.edu.upc.model.entity.Equipo;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.EquipoBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/equipo")
@SessionAttributes("equipo")
public class EquipoController {
	
	@Autowired
	private EquipoBusiness equipoBusiness;
	
	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;
	
	
	@GetMapping
	public String listado(Model model) {
		try {
			List<Equipo> equipos = equipoBusiness.findAll();
			model.addAttribute("equipos", equipos);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/equipo/lista";
	}
	
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			Equipo equipo = new Equipo();
			model.addAttribute("equipo", equipo);
			
			
			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);
			
			
			
		} catch (Exception e) {
			model.addAttribute("error", "Equipo not saved");
		}
		return "/equipo/new"; 
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("equipo") Equipo equipo, Model model, SessionStatus status) {
		try {
			
			equipoBusiness.save(equipo);
			status.setComplete();
			model.addAttribute("success", "Equipo guardado");
		} catch (Exception e) {
			model.addAttribute("error", "Equipo no guardado");
		} 
		return "redirect:/equipo";
	}
	
	@GetMapping("/del/{id}")
	public String del( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Equipo> buscado = equipoBusiness.findById(id);
			if(buscado.isPresent()) {
				equipoBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Equipo not delete");
		}
		return "redirect:/equipo";
	}
	
	@GetMapping("/edit/{id}")
	public String editar( @PathVariable("id") Integer id, Model model) {
		try {
			Optional<Equipo> buscado =  equipoBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("equipo", buscado.get());	
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/equipo/edit";
	}
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<Equipo> buscado =  equipoBusiness.findById(id);
			List<Equipo> equipos = new ArrayList<>();
			if (buscado.isPresent()) {
				equipos.add(buscado.get());				
			} 
			model.addAttribute("equipos", equipos);
			
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/equipo/lista";		
	}
	
	
	
}
