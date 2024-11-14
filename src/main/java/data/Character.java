
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", -> " + name +
                ", status = '" + status + '\'' +
                ", species = '" + species + '\'' +
                ", type = '" + type + '\'' +
                ", gender = '" + gender + '\'';
    }
}