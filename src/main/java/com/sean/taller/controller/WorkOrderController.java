package com.sean.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.services.intfcs.WorkorderService;

@Controller
@RequestMapping("work-ord")
public class WorkOrderController {
	private WorkorderService wos;

	@Autowired
	public WorkOrderController(WorkorderService wos) {
		this.wos = wos;

	}
	
	//****************************** INDEX & INFO******************************
	@GetMapping("")
	public String index(Model model) {
		Iterable<Workorder> iwo = wos.findAll();
		
		if(iwo.iterator().hasNext()){
			model.addAttribute("workorders", iwo);
		}
		
		return "work-ord/index";
	}
	
	@GetMapping("/{id}")
	public String getWorkOrder(Model model, @PathVariable Integer id) {
		Workorder wo = wos.findById(id);
		model.addAttribute("workorder", wo);
		
		return "work-ord/information";
	}
	
	//****************************** ADD ******************************
	@GetMapping("/add")
	public String addWorkOrder(Model model) {
		model.addAttribute("workorder", new Workorder());
		return "work-ord/add";
	}
	
	@PostMapping("/add")
	public String addWorkOrderPost(Model model, @ModelAttribute Workorder wo) {
		wos.add(wo);
		return "redirect:/work-ord";
	}

	//****************************** EDIT ******************************
	@GetMapping("/edit/{id}")
	public String editWorkOrder(Model model, @PathVariable Integer id) {
		Workorder wo = wos.findById(id);
		if (wo == null)
			throw new IllegalArgumentException("Invalid workorder Id:" + id);
		
		model.addAttribute("workorder", wo);
		
		return "work-ord/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model, @PathVariable Integer id, @ModelAttribute Workorder wo) {
		wos.edit(wo);
		return "redirect:/work-ord";
		
	}
	
	//****************************** DELETE ******************************
	@GetMapping("/delete/{id}")
	public String deleteWorkOrder(Model model, @PathVariable Integer id) {
		wos.delete(id);
		return "work-ord/index";
	}
	
	
}
