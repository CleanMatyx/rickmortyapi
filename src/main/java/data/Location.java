package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Clase que contiene los datos de la localización
 */
public class Location implements Serializable {
	private int id;
	private String name;
	private String type;
	private String dimension;
	private List<String> residents;
	private String url;
	private String created;

	/**
	 * Constructor de la clase Location
	 * @param id de la localización
	 * @param name nombre de la localización
	 * @param type tipo de la localización
	 * @param dimension dimensión de la localización
	 * @param residents residentes de la localización
	 * @param url url de la localización
	 * @param created fecha de creación de la localización
	 */
	public Location(int id, String name, String type, String dimension, List<String> residents, String url, String created) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.dimension = dimension;
		this.residents = residents;
		this.url = url;
		this.created = created;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDimension() {
		return dimension;
	}

	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", type=" + type + ", dimension=" + dimension + ", residents="
				+ residents + ", url=" + url + ", created=" + created + "]";
	}
}