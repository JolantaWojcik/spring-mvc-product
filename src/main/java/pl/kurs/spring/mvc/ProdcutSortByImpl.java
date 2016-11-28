package pl.kurs.spring.mvc;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pl.kurs.spring.mvc.model.Product;

@Service
public class ProdcutSortByImpl implements ProductSortByStrategy{
	
	private Map<String, Comparator<Product>> sortByMap;
	
	@PostConstruct
	private void init(){
		sortByMap = new HashMap<>();
		sortByMap.put("id", (a,b)-> a.getId()-b.getId());
		sortByMap.put("product_name", (a,b)->a.getName().compareTo(b.getName()));
		sortByMap.put("price", (a,b)->a.getPrice()-b.getPrice());
		sortByMap.put("category", (a,b)->a.getCategory().compareTo(b.getCategory()));
	}

	@Override
	public Comparator<Product> sortBy(String criteria) {
		// TODO Auto-generated method stub
		return sortByMap.get(criteria);
	}
	
	@Override
	public Comparator<Product> sortByReverse(String criteria) {
		// TODO Auto-generated method stub
		return sortByMap.get(criteria).reversed();
	}

}
