
package data;

import java.io.Serializable;
import java.util.List;

import org.json.simple.JSONObject;

/**
 * Clase que contiene los datos de los personajes
 */
@SuppressWarnings("serial")
public class Character implements Serializable {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
    private JSONObject jsonObject;

    /**
     * Constructor de la clase Personaje
     * @param id
     * @param name
     * @param status
     * @param species
     * @param type
     * @param gender
     * @param origin
     * @param location
     * @param image
     * @param episode
     * @param url
     * @param created
     */
    public Character(int id, String name, String status, String species, String type, String gender, Origin origin,
                     Location location, String image, List<String> episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.origin = origin;
        this.location = location;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
    }

//    /**
//     * Constructor de la clase Personaje
//     * @param jsonObject
//     */
//    public Character(JSONObject jsonObject) {
//        this.id = getIntValue(jsonObject, "id", 0);
//        this.name = getStringValue(jsonObject, "name", "Unknown");
//        this.status = getStringValue(jsonObject, "status", "Unknown");
//        this.species = getStringValue(jsonObject, "species", "Unknown");
//        this.type = getStringValue(jsonObject, "type", "Unknown");
//        this.gender = getStringValue(jsonObject, "gender", "Unknown");
//        this.origin = jsonObject.get("origin") != null ? new Origin((JSONObject) jsonObject.get("origin")) : new Origin("Unknown");
//        this.location = jsonObject.get("location") != null ? new Location((JSONObject) jsonObject.get("location")) : new Location(new JSONObject());
//        this.image = getStringValue(jsonObject, "image", "Unknown");
//        this.episode = jsonObject.get("episode") != null ? (List<String>) jsonObject.get("episode") : List.of("Unknown");
//        this.url = getStringValue(jsonObject, "url", "Unknown");
//        this.created = getStringValue(jsonObject, "created", "Unknown");
//    }

    private int getIntValue(JSONObject jsonObject, String key, int defaultValue) {
        return jsonObject.get(key) != null ? Integer.parseInt(jsonObject.get(key).toString()) : defaultValue;
    }

    private String getStringValue(JSONObject jsonObject, String key, String defaultValue) {
        return jsonObject.get(key) != null ? jsonObject.get(key).toString() : defaultValue;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public Origin getOrigin() {
        return origin;
    }

    public Location getLocation() {
        return location;
    }

    public String getLocationUrl() {
        return location != null ? location.getUrl() : "Unknown";
    }

    public String getImage() {
        return image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", origin=" + origin +
                ", location=" + location +
                ", image='" + image + '\'' +
                ", episode=" + episode +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                ", jsonObject=" + jsonObject +
                '}';
    }
}