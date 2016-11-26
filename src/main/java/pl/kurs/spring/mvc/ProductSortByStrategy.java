package pl.kurs.spring.mvc;

import java.util.Comparator;

import pl.kurs.spring.mvc.model.Product;

public interface ProductSortByStrategy {
	Comparator<Product> sortBy(String criteria);
}
