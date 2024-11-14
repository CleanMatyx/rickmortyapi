package data;

import java.io.Serializable;
import org.json.simple.JSONObject;

/**
 * Clase que contiene los datos del origen
 */
@SuppressWarnings("serial")
public class Origin implements Serializable {

    private String name;
    private String url;

    /**
     * Constructor de la clase Origin
     * @param jsonObject Objeto JSON con los datos del origen
     */
    public Origin(JSONObject jsonObject) {
        this.name = jsonObject.get("name").toString();
        this.url = jsonObject.get("url").toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + ", URL: " + url;
    }
}