package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonUtils {
	@SuppressWarnings("deprecation")
	public static JsonElement readUrlAuth(String web, String token) {
		JsonElement jsonResponse = null;

		try {
			URL url = new URL(web);
			URLConnection uc = url.openConnection();
			System.out.println(uc);
			uc.setRequestProperty("User-Agent", "PostmanRuntime/7.20.1");
			if(token != null) {
				uc.setRequestProperty("X-Auth-Token", token);
			}
			uc.connect();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8))) {
				String lineas = reader.lines().collect(Collectors.joining());
				jsonResponse = JsonParser.parseString(lineas);
			}

		} catch (Exception e) {
			System.out.println("No se ha podido leer la URL: " + web);
			System.out.println(e.getStackTrace() + " " + e.getCause());
		}
		
		return jsonResponse;
	}
	
	public static <T> T apiGsonURL(String web, String token, Class<T> clase) {
		return new Gson().fromJson(readUrlAuth(web, token),  clase);
	}
}
