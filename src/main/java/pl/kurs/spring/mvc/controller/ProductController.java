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
	int sort = 0;

	@PostConstruct
	public void init() {
		database = new ArrayList<Product>();
		database.add(new Product("Book", 123, "science fiction"));
		database.add(new Product("Shoes", 145, "Clothes"));
	}

	//xhr
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewAllProducts(@RequestParam(value= "sortByCriteria", defaultValue="id") String criteria, ModelMap model) {
		order(criteria);
		//database.sort(sortBy.sortBy(criteria));
		model.addAttribute("product", database);
		return "product";
	}
	
	public void orderAsc(String criteria){
		sort = 1;
		database.sort(sortBy.sortBy(criteria));
	}
	public void orderDsc(String criteria){
		sort = 2;
		database.sort(sortBy.sortByReverse(criteria));
	}
	
	public void order(String criteria){
		switch(sort){
			case 0:
				orderAsc(criteria);
				break;
			case 1:
				orderDsc(criteria);
				break;
			case 2:
				orderAsc(criteria);
				break;
		}
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
