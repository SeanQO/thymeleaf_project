package com.sean.taller.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sean.taller.services.intfcs.ProductService;

@Controller
@RequestMapping("prod")
public class ProductController {
	private ProductService ps;

	@Autowired
	public ProductController(ProductService ps) {
		this.ps = ps;

	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("productvendors", ps.findAll());
		return "/prod/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editProductvendor(Model model, @PathVariable("id") Integer id) {

		return "prod/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEditProduct(Model model) {

		return "prod/edit";
	}
	@GetMapping("/add")
	public String addProductvendor(Model model) {
		return "prod/add";
	}

	@PostMapping("/add")
	public String addProductvendorPost(Model model) {
		return "prod/add";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProductvendor(Model model) {
		return "prod/index";
	}
	@GetMapping("/{id}")
	public String getProductvendor(Model model) {

		return "prod/information";
	}
}
