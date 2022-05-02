package com.sean.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sean.taller.services.intfcs.ProductcategoryService;

@Controller
@RequestMapping("prod-categ")
public class ProductCategoryController {
	private ProductcategoryService pcs;

	@Autowired
	public ProductCategoryController(ProductcategoryService pcs) {
		this.pcs = pcs;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", pcs.findAll());
		return "/prod-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "prod-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "prod-categ/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "prod-categ/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "prod-categ/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "prod-categ/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model) {

		return "prod-categ/information";
	}
}
