package com.sean.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sean.taller.services.intfcs.ProductsubcategoryService;

@Controller
@RequestMapping("prod-sub-categ")
public class ProductSubCategoryController {
	private ProductsubcategoryService pscs;

	@Autowired
	public ProductSubCategoryController(ProductsubcategoryService pscs) {
		this.pscs = pscs;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", pscs.findAll());
		return "/prod-sub-categ/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "prod-sub-categ/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "prod-sub-categ/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "prod-sub-categ/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "prod-sub-categ/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "prod-sub-categ/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model) {

		return "prod-sub-categ/information";
	}
}
