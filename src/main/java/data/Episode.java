package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa un episodio de la serie
 * 
 */
public class Episode implements Serializable {
	private int id;
	private String title;
	private String air_date;
	private String episode;
	private ArrayList<String> charactersList;
	private String url;
	private String created;
	
	/**
     * Constructor de la clase Episodio
     * 
     * @param id
     * @param title
     * @param air_date
     * @param episode
     * @param charactersList
     * @param url
     * @param created
     */
	public Episode(int id, String title, String air_date, String episode, ArrayList<String> charactersList, String url,
				   String created) {
		super();
		this.id = id;
		this.title = title;
		this.air_date = air_date;
		this.episode = episode;
		this.charactersList = charactersList;
		this.url = url;
		this.created = created;
	}

	/**
	 * Método que devuelve el id del episodio
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que devuelve el título del episodio
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Método que devuelve la fecha del episodio
	 * @return
	 */
	public String getAir_date() {
		return air_date;
	}

	/**
	 * Método que devuelve el episodio del episodio
	 * @return
	 */
	public String getEpisode() {
		return episode;
	}

	/**
	 * Método que devuelve los personajes del episodio
	 * @return
	 */
	public ArrayList<String> getCharactersList() {
		return charactersList;
	}

	/**
	 * Método que devuelve la url del episodio
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Método que devuelve la fecha de creación del episodio
	 * @return
	 */
	public String getCreated() {
		return created;
	}
}
