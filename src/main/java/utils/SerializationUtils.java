package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import data.Character;
import main.Constants;

/**
 * Clase que contiene los métodos para serializar y deserializar los personajes
 */
public class SerializationUtils {

	/**
	 * Método que serializa los personajes
	 * @param characters
	 * @throws IOException
	 */
	public static void serializePersonajes(List<Character> characters) {
		try {
			File folder = new File(Constants.DATA_FOLDER);
			if (!folder.exists()) {
				folder.mkdirs();
				System.out.println("Folder created: " + Constants.DATA_FOLDER);
			}

			try (FileOutputStream fileOut = new FileOutputStream(Constants.DATA_FOLDER + Constants.CHARACTERS_FILE);
				 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(characters);
				System.out.println(Constants.CHRCTS_SV_MSSG + Constants.DATA_FOLDER + Constants.CHARACTERS_FILE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Método que deserializa los personajes
	 * @return List<Character> characters
	 */
	public static List<Character> deserializePersonajes() {
		List<Character> characters = new ArrayList<>();
		File file = new File(Constants.DATA_FOLDER + Constants.CHARACTERS_FILE);

		if (file.exists() && file.length() > 0) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				characters = (List<Character>) ois.readObject();
				System.out.println(Constants.CHRCTS_LOADED_MSSG + Constants.CHARACTERS_FILE + Constants.DATA_FOLDER);
			} catch (EOFException e) {
				System.out.println("End of file reached unexpectedly: " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("File does not exist or is empty: " + Constants.CHARACTERS_FILE);
		}

		return characters;
	}

	/**
	 * Método que carga los datos de los personajes
	 */
	public static void loadData() {
		Constants.CHARACTERS_LIST = SerializationUtils.deserializePersonajes();
		if (Constants.CHARACTERS_LIST == null || Constants.CHARACTERS_LIST.isEmpty()) {
			System.out.println(Constants.NO_CHRCTS_MSSG);
		} else {
			System.out.println(Constants.CHRCTS_LOADED_MSSG + Constants.CHARACTERS_LIST.size());
		}
	}
}