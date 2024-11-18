
package data;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los datos de los personajes
 */
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
     * @param id identificador del personaje
     * @param name nombre del personaje
     * @param status estado del personaje
     * @param species especie del personaje
     * @param type tipo del personaje
     * @param gender género del personaje
     * @param origin origen del personaje
     * @param location localización del personaje
     * @param image imagen del personaje
     * @param episode episodios en los que aparece el personaje
     * @param url url del personaje
     * @param created fecha de creación del personaje
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