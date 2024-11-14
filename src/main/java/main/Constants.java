
package main;

import java.util.ArrayList;
import java.util.List;

import data.Character;

/**
 * Constants class
 */
public class Constants {

    /**
     * Constants
     */
    public static final String CHARACTERS_URL = "https://rickandmortyapi.com/api/character";
    public static final String EPISODES_URL = "https://rickandmortyapi.com/api/episode";
    public static final String RESULTS_FILE = "resultado.xml";
    public static final String CHARACTERS_FILE = "personajes.dat";
    public static final String DATA_FOLDER = "./data/dataFolder/";
    public static List<Character> CHARACTERS_LIST = new ArrayList<>();
    public static final String NO_CHRCTS_MSSG = "No characters loaded";
    public static final String CHRCTS_LOADED_MSSG = "Characters loaded from: ";
    public static final String CHRCTS_SV_MSSG = "Characters saved at: ";
    public static final String NO_CHRCTS_FND_MSSG = "No characters found with the name: ";

    /**
     * Add a character to the list
     * @param character Character
     */
    public static void addCharacterToList(Character character) {
        boolean exists = CHARACTERS_LIST.stream().anyMatch(p -> p.getId() == character.getId());
        if (!exists) {
            CHARACTERS_LIST.add(character);
        } else {
            System.out.println("The character already exists in the list");
            System.out.println();
        }
    }

    /**
     * Show the welcome message
     */
    public static void showWelcome() {
        System.out.println("██████  ██  ██████ ██   ██      █████  ███    ██ ██████      ███    ███  ██████  ██████  " +
                "████████ ██    ██ ");
        System.out.println("██   ██ ██ ██      ██  ██      ██   ██ ████   ██ ██   ██     ████  ████ ██    ██ ██   ██ " +
                "   ██     ██  ██  ");
        System.out.println("██████  ██ ██      █████       ███████ ██ ██  ██ ██   ██     ██ ████ ██ ██    ██ ██████  " +
                "   ██      ████   ");
        System.out.println("██   ██ ██ ██      ██  ██      ██   ██ ██  ██ ██ ██   ██     ██  ██  ██ ██    ██ ██   ██ " +
                "   ██       ██    ");
        System.out.println("██   ██ ██  ██████ ██   ██     ██   ██ ██   ████ ██████      ██      ██  ██████  ██   ██ " +
                "   ██       ██    ");
        System.out.println("========================================= The Rick and Morty API ========================" +
                "================= ");
    }
}