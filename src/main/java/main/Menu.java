package main;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import data.Character;
import data.Location;
import utils.JsonUtils;
import utils.SerializationUtils;
import utils.XmlUtils;

/**
 * Clase que contiene el menú de la aplicación
 */
public class Menu {
	/**
	 * Scanner para leer la entrada del usuario
	 */
	static Scanner scanner = new Scanner(System.in);

	/**
	 * Enumerado con las opciones del menú
	 */
	enum userOption {
		CONVERSOR_XML, SHOW_XML, OBTAIN_PSNJ, SAVE_PSNJ,
		SHOW_PSNJ, LOCATION_PSNJ, EXIT
	}

	/**
	 * Función principal que muestra el menú y llama a las opciones seleccionadas por el usuario
	 */
	static void menuLoop() throws Exception {
		userOption option;
		do {
			Constants.showWelcome();
			option = showMenu();
			opcionMenu(option);
		} while (option != userOption.EXIT);
	}

	/**
	 * Función que muestra el menú y pide al usuario una selección
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
	 * Función que llama a la opción seleccionada por el usuario
	 *
	 * @param option opción seleccionada por el usuario
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
	 * Función que pide al usuario un episodio y lo convierte a XML
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
	 * Función que muestra el archivo XML obtenido en la función anterior
	 */
	static void showXML() {
		System.out.println("Showing XML file...");
		XmlUtils.readXml();
		cleanConsole();
	}

	/**
	 * Función que pide al usuario un personaje y lo obtiene de la API
	 * Añade el personaje a la lista de personajes de la aplicación
	 * Si el personaje ya está en la lista, no lo añade
	 */
	static void obtainPSNJ() {
		int num;
		do{
			num = getValidIntegerInput("Insert a character to obtain: [1 - 825]");
			if(num < 0 || num > 827){
                System.out.println("Error, choose a valid character");
            } else {
                break;
            }
		} while (true);
		
		try {
			Character character = JsonUtils.getApiJsonEndpoint(Constants.CHARACTERS_URL, num, Character.class);
			Constants.addCharacterToList(character);
			System.out.println("Character obtained: " + character);
		} catch (Exception e) {
			System.out.println("ERROR obtained: " + e.getMessage());
		}
		cleanConsole();
	}


	/**
	 * Función que guarda los personajes en un archivo
	 * Si no hay personajes, no guarda nada
	 * Si no existe la carpeta de datos, la crea y guarda los personajes
	 */
	static void savePSNJ() {
		List<Character> characters = Constants.CHARACTERS_LIST;
		SerializationUtils.serializePersonajes(characters);
		cleanConsole();
	}

	/**
	 * Función que muestra los personajes de la lista de personajes de la aplicación
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
	 * Función que pide al usuario un personaje y muestra su localización
	 * Si no se encuentra el personaje, muestra un mensaje de error y vuelve al menú
     */
	static void locationPSNJ() throws Exception {
		System.out.println("Insert the name of the character to get the location: ");
		String name = scanner.nextLine();
		List<Character> matchingCharacters = Constants.CHARACTERS_LIST != null ?
				Constants.CHARACTERS_LIST.stream()
						.filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
						.toList() :
				Collections.emptyList();

		if (!matchingCharacters.isEmpty()) {
			for (Character character : matchingCharacters) {
				Location location = JsonUtils.getApiJson(character.getLocation().getUrl(), Location.class);
				System.out.println("Location of " + character.getName() + ":");
				System.out.println("\tName: " + location.getName() + "\n\tLocation: " + location.getType()
						+ "\n\tDimension: " + location.getDimension());
			}
		} else {
			System.out.println(Constants.NO_CHRCTS_FND_MSSG + name);
		}
		cleanConsole();
	}

	/**
	 * Función que cierra la aplicación
	 */
	static void exit() {
		System.exit(0);
	}

	/**
	 * Función que pide al usuario un entero y comprueba que sea válido
	 * @param input input a comprobar, si no es un entero válido, se pide de nuevo
	 * @return el entero introducido por el usuario si es válido
	 */
	private static int getValidIntegerInput(String input) {
		int result;
		while (true) {
			try {
				System.out.println(input);
				result = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
		return result;
	}

	/**
	 * Función que limpia la consola
	 */
	private static void cleanConsole() {
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
}