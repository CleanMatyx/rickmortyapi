package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ApiUtils {
	// USAREMOS UNA List<String> PARA ALMACENAR LO LEIDO
	// PODREMOS TENER 1 LINEA POR CADA ELEMENTO, UTIL PARA XML Y JSON
	public static List<String> readUrlList(String web) {

		List<String> lineas = null;

		try {
			@SuppressWarnings("deprecation")
			URL url = new URL(web);
			URLConnection uc = url.openConnection();
			uc.setRequestProperty("User-Agent", "PostmanRuntime/7.20.1");
			uc.connect();

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8))) {
				lineas = reader.lines().collect(Collectors.toList());
			}

			return lineas;

		} catch (Exception e) {
			System.out.println("No se ha podido leer la URL: " + web);
			System.out.println(e.getStackTrace() + " " + e.getCause());
		}
		return lineas;
	}
}
