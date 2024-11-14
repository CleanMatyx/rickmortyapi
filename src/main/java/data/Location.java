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

	public Location(JSONObject jsonObject) {
		this.id = getIntValue(jsonObject, "id", -1);
		this.name = getStringValue(jsonObject, "name", "Unknown");
		this.type = getStringValue(jsonObject, "type", "Unknown");
		this.dimension = getStringValue(jsonObject, "dimension", "Unknown");
		this.residents = new ArrayList<>();
		if (jsonObject.get("residents") != null) {
			for (Object resident : (JSONArray) jsonObject.get("residents")) {
				this.residents.add(resident.toString());
			}
		}
		this.url = getStringValue(jsonObject, "url", "Unknown");
		this.created = getStringValue(jsonObject, "created", "Unknown");
	}

	public Location(int id, String name, String type, String dimension, List<String> residents, String url, String created) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.dimension = dimension;
		this.residents = residents;
		this.url = url;
		this.created = created;
	}

	private int getIntValue(JSONObject jsonObject, String key, int defaultValue) {
		return jsonObject.get(key) != null ? Integer.parseInt(jsonObject.get(key).toString()) : defaultValue;
	}

	private String getStringValue(JSONObject jsonObject, String key, String defaultValue) {
		return jsonObject.get(key) != null ? jsonObject.get(key).toString() : defaultValue;
	}

	public int getId() {
		return id;
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

	public List<String> getResidents() {
		return residents;
	}

	public String getUrl() {
		return url;
	}

	public String getCreated() {
		return created;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public void setResidents(List<String> residents) {
		this.residents = residents;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", type=" + type + ", dimension=" + dimension + ", residents="
				+ residents + ", url=" + url + ", created=" + created + "]";
	}
}