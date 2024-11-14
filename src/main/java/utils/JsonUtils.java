package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import org.json.simple.JSONObject;

public class JsonUtils {

    public static <T> T getApiJsonEndpoint(String urlApi, int endpoint, Class<T> genericClass) throws Exception {
        String urlString = urlApi + "/" + endpoint;
        URL url = new URL(urlString);
        Gson gson = new Gson();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Convierto el JSON en un objeto del tipo genérico T
            return gson.fromJson(response.toString(), genericClass);
        }
    }

    public static <T> T getApiJson(String urlApi, Class<T> clazz) throws Exception {
        String urlString = urlApi;
        URL url = new URL(urlString);
        Gson gson = new Gson();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Convierto el JSON en un objeto del tipo genérico T
            return gson.fromJson(response.toString(), clazz);
        }
    }

    public static JSONObject getApiJsonEpisode(String urlApi, int endpoint) throws Exception {
        String urlString = urlApi + "/" + endpoint;
        URL url = new URL(urlString);
        Gson gson = new Gson();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Convierto el JSON en un objeto del tipo JSONObject
            return gson.fromJson(response.toString(), JSONObject.class);
        } finally {
            conn.disconnect();
        }
    }
    
    
}

