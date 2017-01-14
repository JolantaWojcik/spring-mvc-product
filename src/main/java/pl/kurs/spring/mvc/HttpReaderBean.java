package pl.kurs.spring.mvc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class HttpReaderBean {

	public int countWord(String word, String httpAddress) {
		String source = getUrlSource(httpAddress);
		int count = 0;
		Pattern pat = Pattern.compile(word);
		Matcher matcher = pat.matcher(source);
		while(matcher.find()){
			count++;
		}
		return count;
	}

	public String getUrlSource(String httpAddress) {
		try {
			URL oracle = new URL(httpAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

			StringBuilder sb = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);
			in.close();
			return sb.toString();
		} catch (Exception e) {
			return "";
		}

	}

}
