/**
 * Clase de utilidades que proporciona métodos útiles para la interacción con el
 * usuario y la manipulación de objetos.
 * @author (Sara Cubero) 
 * @version (1.0)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class Utilidades {
	/**
	 * Imprime un menú en la consola con las opciones proporcionadas.
	 *
	 * @param opciones Las opciones del menú.
	 */
	public static void imprimirMenu(String[] opciones) {
		for (String opcion : opciones) {
			System.out.println(opcion);
		}
	}

	/**
	 * Imprime los atributos de un objeto utilizando reflexión.
	 *
	 * @param objeto    El objeto del cual imprimir los atributos.
	 * @param atributos Los nombres de los atributos a imprimir.
	 */
	public static void imprimirObjeto(Object objeto, String[] atributos) {
		StringBuilder sb = new StringBuilder();
		for (String atributo : atributos) {
			try {
				Method metodo = objeto.getClass()
						.getMethod("get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1));
				Object valor = metodo.invoke(objeto);
				sb.append(atributo).append(": ").append(valor).append("\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(sb.toString());
	}

	/**
	 * Lee un número entero desde la entrada estándar.
	 *
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @return El número entero ingresado.
	 */
	public static int getInt(String mensaje) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean entradaValida = false;
		int numero = 0;

		while (!entradaValida) {
			try {
				System.out.print(mensaje);
				numero = Integer.parseInt(reader.readLine());
				entradaValida = true;
			} catch (NumberFormatException e) {
				System.out.println("Error: debes ingresar un número entero.");
			} catch (IOException e) {
				System.out.println("Error al leer la entrada del usuario.");
			}
		}

		return numero;
	}

	/**
	 * Lee un número decimal desde la entrada estándar.
	 *
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @param limit   El límite máximo para el número decimal. 0.0 si no hay límite.
	 * @return El número decimal ingresado.
	 */
	public static double getDouble(String mensaje, double limit) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean entradaValida = false;
		double numero = 0;

		while (!entradaValida) {
			try {
				System.out.print(mensaje);
				numero = Double.parseDouble(reader.readLine());

				if (limit == 0.0 || numero <= limit)
					entradaValida = true;
				else
					System.out.println("Error: la cantidad ingresada no puede ser superior a: " + limit + ".");
			} catch (NumberFormatException e) {
				System.out.println("Error: debes ingresar un número decimal.");
			} catch (IOException e) {
				System.out.println("Error al leer la entrada del usuario.");
			}
		}

		return numero;
	}

	/**
	 * Lee una cadena de texto desde la entrada estándar.
	 *
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @return La cadena de texto ingresada.
	 */
	public static String getString(String mensaje) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";

		try {
			System.out.print(mensaje);
			input = reader.readLine();
		} catch (IOException e) {
			System.out.println("Error al leer la entrada del usuario.");
		}

		return input;
	}

	/**
	 * Lee una respuesta booleana ("S" o "N") desde la entrada estándar.
	 *
	 * @param mensaje El mensaje que se muestra al usuario.
	 * @return `true` si la respuesta es "S", `false` si la respuesta es "N".
	 */
	public static boolean getBoolean(String mensaje) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean input = false;
		boolean valido = false;

		while (!valido) {
			try {
				System.out.print(mensaje + " (S/N): ");
				String respuesta = reader.readLine().toUpperCase();
				if (respuesta.equals("S")) {
					input = true;
					valido = true;
				} else if (respuesta.equals("N")) {
					input = false;
					valido = true;
				} else {
					System.out.println("La respuesta ingresada no es válida. Por favor, ingrese S o N.");
				}
			} catch (IOException e) {
				System.out.println("Error al leer la entrada del usuario.");
			}
		}

		return input;
	}

}
