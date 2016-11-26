package pl.kurs.spring.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.kurs.spring.mvc.ProductSortByStrategy;
import pl.kurs.spring.mvc.model.Product;

@Controller
@RequestMapping("/product")
public class ProductController {
	private List<Product> database;
	@Autowired
	private ProductSortByStrategy sortBy;

	@PostConstruct
	public void init() {
		database = new ArrayList<Product>();
		database.add(new Product("Book", 123, "science fiction"));
		database.add(new Product("Shoes", 145, "Clothes"));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewAllProducts(@RequestParam(value= "sortByCriteria", defaultValue="id") String criteria, ModelMap model) {
		database.sort(sortBy.sortBy(criteria));
		model.addAttribute("product", database);
		return "product";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute Product product, ModelMap model) {
		database.add(product);
		return viewAllProducts("id", model);
	}
	
	@RequestMapping(value = "/remove/", method = RequestMethod.POST)
	public String removeProduct(@RequestParam("id") int id, ModelMap model) {
		Product toRemove = database.stream().filter(e-> e.getId()==id).findFirst().get();
		database.remove(toRemove);
		return viewAllProducts("id", model);
	}
}
