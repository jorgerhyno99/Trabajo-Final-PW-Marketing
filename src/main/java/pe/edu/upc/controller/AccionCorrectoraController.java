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

import pe.edu.upc.model.entity.AccionCorrectora;
import pe.edu.upc.model.entity.PlanDeMarketing;
import pe.edu.upc.service.AccioncorrectoraBusiness;
import pe.edu.upc.service.PlandemarketingBusiness;

@Controller
@RequestMapping("/accioncorrectora")
@SessionAttributes("accioncorrectora")
public class AccionCorrectoraController {

	@Autowired
	private AccioncorrectoraBusiness accioncorrectoraBusiness;

	@Autowired
	private PlandemarketingBusiness plandemarketingBusiness;

	@GetMapping
	public String listado(Model model) {
		try {
			List<AccionCorrectora> accioncorrectoras = accioncorrectoraBusiness.findAll();
			model.addAttribute("accioncorrectoras", accioncorrectoras);
		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/accioncorrectora/lista";
	}

	@GetMapping("/new")
	public String nuevo(Model model) {
		try {
			AccionCorrectora accioncorrectora = new AccionCorrectora();
			model.addAttribute("accioncorrectora", accioncorrectora);

			List<PlanDeMarketing> planDeMarketings = plandemarketingBusiness.findAll();
			model.addAttribute("planDeMarketings", planDeMarketings);

		} catch (Exception e) {
			model.addAttribute("error", "AccionCorrectora not saved");
		}
		return "/accioncorrectora/new";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("accioncorrectora") AccionCorrectora accioncorrectora, Model model,
			SessionStatus status) {
		try {

			accioncorrectoraBusiness.save(accioncorrectora);
			status.setComplete();
			model.addAttribute("success", "AccionCorrectora guardado");
		} catch (Exception e) {
			model.addAttribute("error", "AccionCorrectora no guardado");
		}
		return "redirect:/accioncorrectora";
	}

	@GetMapping("/del/{id}")
	public String del(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<AccionCorrectora> buscado = accioncorrectoraBusiness.findById(id);
			if (buscado.isPresent()) {
				accioncorrectoraBusiness.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("error", "AccionCorrectora not delete");
		}
		return "redirect:/accioncorrectora";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		try {
			Optional<AccionCorrectora> buscado = accioncorrectoraBusiness.findById(id);
			if (buscado.isPresent()) {
				model.addAttribute("accioncorrectora", buscado.get());
				List<PlanDeMarketing> locations = plandemarketingBusiness.findAll();
				model.addAttribute("locations", locations);
			} else {
				model.addAttribute("error", "Campaña no encontrada");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Campaña no encontrada");
		}
		return "/accioncorrectora/edit";
	}

	@GetMapping("/buscar")
	public String buscar(@RequestParam("txtId") Integer id, Model model) {
		try {
			Optional<AccionCorrectora> buscado = accioncorrectoraBusiness.findById(id);
			List<AccionCorrectora> accioncorrectoras = new ArrayList<>();
			if (buscado.isPresent()) {
				accioncorrectoras.add(buscado.get());
			}
			model.addAttribute("departamentos", accioncorrectoras);

		} catch (Exception e) {
			model.addAttribute("error", "Error en obtener la lista");
		}
		return "/accioncorrectora/lista";
	}

}
