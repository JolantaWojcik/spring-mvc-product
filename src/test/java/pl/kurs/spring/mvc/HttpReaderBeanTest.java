package pl.kurs.spring.mvc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HttpReaderBeanTest.HttpReaderBeanConfigurationTest.class)
public class HttpReaderBeanTest {

	// F.I.R.S.T

	// Fast
	// Independent
	// Repeatable
	// SelfValidating
	// Timely (pisz test przed kodem produkcyjnym)

	@Autowired
	private HttpReaderBean httpReaderBean;

	@Test
	public void shouldCountHTMLinGooglePL() {
		// given
		String word = "html";
		String address1 = "http://google.pl";
		String address2 = "http://onet.pl";

		// nauczmy obiekt ze jezeli bedzie uzywal metody getUrlSource z
		// argumentem google.pl to ma zwrocic jakis stały teskt.
		Mockito.when(httpReaderBean.getUrlSource(address1)).thenReturn("<html>moje dane</html>");
		Mockito.when(httpReaderBean.getUrlSource(address2)).thenReturn("strona nie istnieje html");
		Mockito.when(httpReaderBean.countWord(Mockito.anyString(), Mockito.anyString())).thenCallRealMethod();

		// when
		int count1 = httpReaderBean.countWord(word, address1);
		int count2 = httpReaderBean.countWord(word, address2);
		// then
		Assert.assertTrue(count1 == 2);
		Assert.assertTrue(count2 == 1);

	}

	@Configuration
	public static class HttpReaderBeanConfigurationTest {
		@Bean
		public HttpReaderBean httpReaderBean() {
			return Mockito.mock(HttpReaderBean.class);
			// kazda metoda typu void - nic nie robi
			// kazda metoda ktora zwraca obiekt, - nic nie robi tylko zwraca
			// nulla
			// prymitywy zwracaja domyslne wartosci

			// obiekt mockowany mozemy dynamicznie uczyc co on ma zwraca dla
			// danej metody
		}
	}

}
