
package main;

import java.util.ArrayList;
import java.util.List;

import data.Character;

public class Constants {

    public static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    public static final String LOCATION_URL = "https://rickandmortyapi.com/api/location";
    public static final String EPISODES_URL = "https://rickandmortyapi.com/api/episode";
    public static final String RESULTS_FILE = "resultado.xml";
    public static final String CHARACTERS_FILE = "personajes.dat";
    public static final String DATA_FOLDER = "./data/dataFolder/";
    public static List<Character> CHARACTERS_LIST = new ArrayList<>();
    public static final String NO_CHRCTS_MSSG = "No characters loaded";
    public static final String CHRCTS_LOADED_MSSG = "Characters loaded from: ";
    public static final String NO_CHRCTS_SV_MSSG = "No characters saved";
    public static final String CHRCTS_SV_MSSG = "Characters saved at: ";
    public static final String NO_CHRCTS_FND_MSSG = "No characters found with the name: ";

    public static void addPersonaje(Character character) {
        boolean exists = CHARACTERS_LIST.stream().anyMatch(p -> p.getId() == character.getId());
        if (!exists) {
            CHARACTERS_LIST.add(character);
        } else {
            System.out.println("The character already exists in the list");
            System.out.println();
        }
    }

	public static Character getCharacter(String nombre) {
		Character character = null;
		for (Character p : CHARACTERS_LIST) {
			if (p.getName().toLowerCase().contains(nombre.toLowerCase())) {
				character = p;
			}
		}
		return character;
	}
}