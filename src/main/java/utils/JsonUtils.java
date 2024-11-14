package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import org.json.simple.JSONObject;

/**
 * Clase que contiene los métodos para obtener datos de una API en formato JSON
 */
public class JsonUtils {

    /**
     * Función que obtiene los datos de un endpoint de una API en formato JSON
     * @param urlApi URL de la API
     * @param endpoint Endpoint de la API
     * @param genericClass Clase genérica
     * @return T Objeto genérico
     * @param <T> Clase genérica de retorno
     * @throws Exception Excepción en caso de error
     */
    public static <T> T getApiJsonEndpoint(String urlApi, int endpoint, Class<T> genericClass) throws Exception {
        if (!urlApi.startsWith("http://") && !urlApi.startsWith("https://")) {
            urlApi = "http://" + urlApi;
        }
        String urlString = urlApi + "/" + endpoint;
        return getApiJson(urlString, genericClass);
    }

    /**
     * Función que obtiene los datos de una API en formato JSON
     * @param urlApi URL de la API
     * @param genericClass Clase genérica
     * @return T Objeto genérico
     * @param <T> Clase genérica de retorno
     * @throws Exception Excepción en caso de error
     */
    public static <T> T getApiJson(String urlApi, Class<T> genericClass) throws Exception {
        if (!urlApi.startsWith("http://") && !urlApi.startsWith("https://")) {
            urlApi = "http://" + urlApi;
        }
        return getApiJson(urlApi, genericClass, new Gson());
    }

    /**
     * Función que obtiene los datos de un episodio de una API en formato JSON
     * @param urlApi URL de la API
     * @param endpoint Endpoint de la API
     * @return JSONObject Objeto JSON
     * @throws Exception Excepción en caso de error
     */
    public static JSONObject getApiJsonEpisode(String urlApi, int endpoint) throws Exception {
        if (!urlApi.startsWith("http://") && !urlApi.startsWith("https://")) {
            urlApi = "http://" + urlApi;
        }
        String urlString = urlApi + "/" + endpoint;
        return getApiJson(urlString, JSONObject.class, new Gson());
    }

    /**
     * Función privado que obtiene los datos de una API en formato JSON
     * @param urlString URL completa de la API
     * @param genericClass Clase genérica
     * @param gson Instancia de Gson
     * @return T Objeto genérico
     * @param <T> Clase genérica de retorno
     * @throws Exception Excepción en caso de error
     */
    private static <T> T getApiJson(String urlString, Class<T> genericClass, Gson gson) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            return gson.fromJson(response.toString(), genericClass);
        } finally {
            conn.disconnect();
        }
    }
}