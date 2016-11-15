package pl.kurs.spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kurs.spring.mvc.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	private List<Product> database;

	@PostConstruct
	public void init() {
		database = new ArrayList<Product>();
		database.add(new Product("Book", 123, "science fiction"));
		database.add(new Product("Shoes", 145, "Clothes"));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewAllProducts(ModelMap model) {
		model.addAttribute("product", database);
		return "product";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute Product product, ModelMap model) {
		database.add(product);
		return viewAllProducts(model);
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeProduct(@ModelAttribute Product product, ModelMap model) {
		database.remove(product.getId());
		return viewAllProducts(model);
	}
}
