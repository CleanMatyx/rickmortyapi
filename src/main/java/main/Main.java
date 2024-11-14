package main;

import utils.SerializationUtils;

/**
 * Clase Main que inicia el programa.
 * @autor Matias E. Borra Quiroz
 */

public class Main {

	/**
	 * Método main que inicia el programa.
	 */
	public static void main(String[] args) throws Exception {
		SerializationUtils.loadData();
		Menu.menuLoop();
	}
}