package main;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.logging.Logger;

import data.Character;

import data.Location;
import utils.JsonUtils;
import utils.SerializationUtils;
import utils.XmlUtils;

/**
 * Clase que contiene el menú de la aplicación
 *
 */
public class Menu {
	/**
	 * Scanner para leer la entrada del usuario
	 */
	static Scanner scanner = new Scanner(System.in);
	private static final Logger logger = Logger.getLogger(Menu.class.getName());

	/**
	 * Enumerado con las opciones del menú
	 */
	enum userOption {
		CONVERSOR_XML, SHOW_XML, OBTAIN_PSNJ, SAVE_PSNJ,
		SHOW_PSNJ, LOCATION_PSNJ, EXIT
	}

	/**
	 * Método que muestra el menú y pide al usuario una opción
	 */
	static void menuLoop() throws Exception {
		userOption option;
		do {
			option = showMenu();
			opcionMenu(option);
		} while (option != userOption.EXIT);
	}

	/**
	 * Método que muestra el menú y pide al usuario una opción
	 * Si la selección es diferente al número de opciones, se vuelve a pedir la selección
	 * Impide también numeros negativos
	 *
	 * @return la opción seleccionada por el usuario
	 */
	static userOption showMenu() {
		System.out.println("Choose an option below: ");
		System.out.println();
		System.out.println("1. Converse XML");
		System.out.println("2. Show XML");
		System.out.println("3. Obtain Character");
		System.out.println("4. Save Character");
		System.out.println("5. Show Characters");
		System.out.println("6. Location of Character");
		System.out.println("7. Exit");

		int option = Integer.parseInt(scanner.nextLine());
		if (option < 1 || option > userOption.values().length) {
			System.out.println("Error, choose a valid option");
			return showMenu();
		}
		return userOption.values()[option - 1];
	}

	/**
	 * Método que llama a los métodos de las opciones del menú
	 *
	 * @param option opción seleccionada por el usuario
	 * @return la opción seleccionada por el usuario
	 */
	static void opcionMenu(userOption option) throws Exception {
		switch (option) {
			case CONVERSOR_XML:
				conversorXML();
				break;
			case SHOW_XML:
				showXML();
				break;
			case OBTAIN_PSNJ:
				obtainPSNJ();
				break;
			case SAVE_PSNJ:
				savePSNJ();
				break;
			case SHOW_PSNJ:
				showPSNJ();
				break;
			case LOCATION_PSNJ:
				locationPSNJ();
				break;
			case EXIT:
				exit();
				break;
			default:
				System.out.println("Error, choose a valid option");
				break;
		}
	}

	/**
	 * Método que pide al usuario un episodio (un entero) y genera un archivo XML con los datos de ese episodio
	 */
	static void conversorXML() {
		int episode;
		do{
			episode = getValidIntegerInput("Insert an episode to convert to XML: [1 - 51]");
			if (episode <= 0 || episode > 51) {
				System.out.println("Error, choose a valid episode");
			} else {
				break;
			}
		} while (true);
		XmlUtils.convertToXml(episode);
		cleanConsole();
	}

	/**
	 * Método que muestra el contenido de un archivo XML generado por el programa en formato JSON
	 */
	static void showXML() {
		System.out.println("Showing XML file...");
		XmlUtils.readXml();
		cleanConsole();
	}

	/**
	 * Método que pide al usuario un personaje (un entero) y obtiene los datos de ese personaje
	 */
	static void obtainPSNJ() {
		int num;
		do{
			num = getValidIntegerInput("Insert a character to obtain: [1 - 826]");
		} while (num < 0 && num > 827);

		try {
			Character character = JsonUtils.getApiJsonEndpoint(Constants.CHARACTERS_URL, num, Character.class);
			Constants.addPersonaje(character);
			System.out.println("Character obtained: " + character);
		} catch (Exception e) {
			System.out.println("ERROR obtained: " + e.getMessage());
		}
		cleanConsole();
	}


	/**
	 * Método que guarda los personajes en un archivo
	 */
	static void savePSNJ() {
		List<Character> characters = Constants.CHARACTERS_LIST;
		SerializationUtils.serializePersonajes(characters);
		System.out.println("Characters saved successfully at: " + Constants.DATA_FOLDER + Constants.CHARACTERS_FILE);
		cleanConsole();
	}

	/**
	 * Método que muestra los personajes cargados
	 */
	static void showPSNJ() {
		if (Constants.CHARACTERS_LIST != null) {
			for (Character p : Constants.CHARACTERS_LIST) {
				System.out.println(p);
			}
		} else {
			System.out.println("No characters loaded");
		}
		cleanConsole();
	}

	/**
	 * Método que pide al usuario un personaje y muestra la localización de ese personaje
	 * @throws Exception
	 */
	static void locationPSNJ() throws Exception {
		System.out.println("Insert the name of the character to get the location: ");
		String name = scanner.nextLine();
		List<Character> matchingCharacters = Constants.CHARACTERS_LIST != null ?
				Constants.CHARACTERS_LIST.stream()
						.filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
						.collect(Collectors.toList()) :
				Collections.emptyList();

		if (!matchingCharacters.isEmpty()) {
			for (Character character : matchingCharacters) {
				Location location = JsonUtils.getApiJson(character.getLocation().getUrl(), Location.class);
				System.out.println("Location of " + character.getName() + ":");
				System.out.println("\tName: " + location.getName() + "\n\tLocation: " + location.getType()
						+ "\n\tDimension: " + location.getDimension());
			}
		} else {
			System.out.println("No characters found with the name: " + name);
		}
		cleanConsole();
	}

	static void exit() {
		System.exit(0);
	}

	/**
	 * Método que pide al usuario un entero y comprueba que sea un entero válido
	 * @param prompt
	 * @return el entero introducido por el usuario
	 */
	private static int getValidIntegerInput(String prompt) {
		int result;
		while (true) {
			try {
				System.out.println(prompt);
				result = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
		return result;
	}

	/**
	 * Método que limpia la consola
	 */
	private static void cleanConsole() {
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
}