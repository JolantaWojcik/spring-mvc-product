package pl.kurs.spring.mvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.kurs.spring.mvc.model.Product;

//adnotacja runwith mowi nam o tym ze test bedzie wzbogacony o kontekst springowy i bedzie mozna w nim np: 
//uzywac adnotacji @Autowired.
@RunWith(SpringJUnit4ClassRunner.class)
//jednak jak juz mamy kontekst springowy to koniecznie trzeba podac jego konfiguracje (u nas w przypadku 
//statycznej klasy)
@ContextConfiguration(classes=ProductSortByImplTest.ProductSortByStrategyCofigurationTest.class)
public class ProductSortByImplTest {

	@Autowired
	private ProductSortByStrategy productStrategy;

	@Test
	public void shouldSortProductsByName() {
		// given
		List<Product> products = Arrays.asList(new Product("Z", 10, "RTV"), new Product("A", 20, "AGD"));
		String criteria = "product_name";
		// when
		Collections.sort(products, productStrategy.sortBy(criteria));
		// then
		Assert.assertTrue(products.get(0).getName().equals("A"));
		Assert.assertTrue(products.get(1).getName().equals("Z"));
	}

	@Configuration
	//springowa konfiguracja testu: UWAGA, nigdy nie uzywamy zadnej konfiguracji z main.
	//poniewaz mozna ona zawierac za duzo zaleznosci, ladowac sie za dlugo, i ladowac niepotrzebne beany 
	//do testu ktory i tak nie bedzie ich uzywal.
	public static class ProductSortByStrategyCofigurationTest {
		@Bean
		public ProductSortByStrategy productSortByStrategy() {
			return new ProdcutSortByImpl();
		}
	}

}
