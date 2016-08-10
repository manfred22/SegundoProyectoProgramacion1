/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Paquetes
package Buscaminas;
//Importes
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 **
 ** @author Manfred Martineli Rojas
 ** @date 2016-07-24
 **/
public class Menu{
            /** The castellano. */
    public static boolean castellano = false;

    /** The ingles. */
    public static boolean ingles = false;
    public Menu()throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean corriendo = true;
	boolean corriendo2 = true;
	boolean corriendo3 = true;
	boolean lenguaje = true;
	boolean partidaMenu = true;
	int dimensiones = 0;
	int dimensiones2 = 0;
	int numeroMinas = 0;

	// Selector de idioma basico
	while (corriendo3) {
	    System.out.println("Por favor, selecciona el idioma");
	    System.out.println("Please, select your language");
	    System.out.println("a) Español");
	    System.out.println("b) English");
	    String idioma = br.readLine();
	    EscribirRecord gestorGuardado = new EscribirRecord();
	    String fileName = "Victorias.txt";
	    String nombre;

	    // Solicitud del nombre de usuario para el record
	    System.out.println("Por favor, introduce tu nombre");
	    System.out.println("Please, input your name");
	    nombre = br.readLine();
	    do {
		// Menu castellano
		if (idioma.equals("a") | idioma.equals("Español")) {
		    castellano = true;
		    while (corriendo) {
			corriendo2 = true;
			System.out.println("Escoge una opcion (a, b, c, d, e)");
			System.out.println("a) Empezar partida");
			System.out.println("b) Ver puntuaciones");
			System.out.println("c) Cambiar usuario");
			System.out.println("d) Salir.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
					.println("Introduzca las dimensiones del tablero y la cantidad de minas\na) Principiante: 10 minas y 9x9\nb) Intermedio: 40 minas y 16x16\nc) Avanzado: 99 minas y 16x30\nd) Personalizado: introduce tus propios valores\ne) Volver al menu principal");
				String lineaPartida = br.readLine();
				do {
				    if (lineaPartida.equals("a")) {
					// Dimensiones del tablero por defecto.
					// Se replica en los siguientes
					dimensiones = 9;
					dimensiones2 = 9;
					numeroMinas = 10;
					// Inicio del tiempo de juego para el
					// record
					long inicioJuego = System.nanoTime();
					// Creacion del tablero de juego
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida termino en victoria, se
					// escribe el record
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("b")) {
					dimensiones = 16;
					dimensiones2 = 16;
					numeroMinas = 40;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}

				    }
				    if (lineaPartida.equals("c")) {
					dimensiones = 16;
					dimensiones2 = 30;
					numeroMinas = 99;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("d")) {
					// Personalizado, se solicitan los datos
					// al usuario
					System.out
						.println("Introduzca las dimensiones del lado 1 (Hasta 100)");
					dimensiones = (Integer.parseInt(br
						.readLine()));
					if (dimensiones > 100) {
					    System.err
						    .println("Error. Maximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca las dimensiones del lado 2 (Hasta 100)");
					dimensiones2 = (Integer.parseInt(br
						.readLine()));
					if (dimensiones2 > 100) {
					    System.err
						    .println("Error. Maximo 100, por favor\n");
					    break;
					}
					System.out
						.println("Introduzca el numero de minas");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensiones
						* dimensiones2) {
					    System.err
						    .println("Error. Todas las casillas seran minas, la victoria es imposible\n");
					    break;
					}
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al menu principal
					partidaMenu = false;
					corriendo2 = false;
				    }
				    partidaMenu = false;
				} while (partidaMenu);

			    }
			    if (linea.equals("b")) {
				// Leer el fichero de victorias
				gestorGuardado.getWins();
				corriendo2 = false;
			    }
			    if (linea.equals("c")) {
				// Cambio del nombre de usuario
				System.out
					.println("Introduce el nombre de usuario");
				nombre = br.readLine();
				corriendo2 = false;
			    }
			    if (linea.equals("d")) {
				// Salir del programa
				corriendo3 = false;
				lenguaje = false;
				corriendo = false;
				corriendo2 = false;
			    }

			    // Error en la seleccion, pausa y regreso al menu
			    if (!(linea.equals("a") || linea.equals("b")
				    || linea.equals("c") || linea.equals("d"))) {
				System.err
					.println("Error. Por favor, introduce a, b, c , d");
				try {
				    Thread.sleep(2000);
				} catch (InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				corriendo2 = false;
			    }
			} while (corriendo2);

		    }
		}
		// Menu ingles
		if (idioma.equals("b") | idioma.equals("English")) {
		    ingles = true;
		    while (corriendo) {
			corriendo2 = true;
			System.out.println("Choose an option (a, b, c, d, e)");
			System.out.println("a) Start game");
			System.out.println("b) See records");
			System.out.println("c) Change username");
			System.out.println("d) Exit.");

			String linea = br.readLine();

			do {
			    // Tableros por defecto o personalizado
			    if (linea.equals("a")) {
				System.out
					.println("Enter the dimensions of the board and the number of mines\na) Beginner: 10 mines y 9x9\nb) Intermediate: 40 mines y 16x16\nc) Advanced: 99 mines y 16x30\nd) Custom: enter your own values\ne) Return to main menu");
				String lineaPartida = br.readLine();
				do {
				    if (lineaPartida.equals("a")) {
					// Dimensiones del tablero por defecto.
					// Se replica en los siguientes
					dimensiones = 9;
					dimensiones2 = 9;
					numeroMinas = 10;
					// Inicio del tiempo de juego para el
					// record
					long inicioJuego = System.nanoTime();
					// Creacion del tablero de juego
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					// Fin del tiempo de partida
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					// Si la partida termino en victoria, se
					// escribe el record
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("b")) {
					dimensiones = 16;
					dimensiones2 = 16;
					numeroMinas = 40;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}

				    }
				    if (lineaPartida.equals("c")) {
					dimensiones = 16;
					dimensiones2 = 30;
					numeroMinas = 99;
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("d")) {
					// Personalizado, se solicitan los datos
					// al usuario
					System.out
						.println("Enter side 1 size (Until 100)");
					dimensiones = (Integer.parseInt(br
						.readLine()));
					if (dimensiones > 100) {
					    System.err
						    .println("Error. Maximum 100, please\n");
					    break;
					}
					System.out
						.println("Enter side 2 size (Until 100)");
					dimensiones2 = (Integer.parseInt(br
						.readLine()));
					if (dimensiones2 > 100) {
					    System.err
						    .println("Error. Maximum 100, please\n");
					    break;
					}
					System.out
						.println("Enter the number of mines");
					numeroMinas = (Integer.parseInt(br
						.readLine()));
					if (numeroMinas >= dimensiones
						* dimensiones2) {
					    System.err
						    .println("Error. All boxes will be mines, victory is impossible\n");
					    break;
					}
					long inicioJuego = System.nanoTime();
					TableroJuego juego = new TableroJuego(
						dimensiones, dimensiones2,
						numeroMinas);
					long tiempoJuego = System.nanoTime()
						- inicioJuego;
					if (TableroJuego.record) {
					    gestorGuardado.gestionarGuardado(
						    tiempoJuego, fileName,
						    nombre);
					}
				    }
				    if (lineaPartida.equals("e")) {
					// Volver al menu principal
					partidaMenu = false;
					corriendo2 = false;
				    }
				    partidaMenu = false;
				} while (partidaMenu);

			    }
			    if (linea.equals("b")) {
				// Leer el fichero de victorias
				gestorGuardado.getWins();
				corriendo2 = false;
			    }
			    if (linea.equals("c")) {
				// Cambio del nombre de usuario
				System.out.println("Enter the username");
				nombre = br.readLine();
				corriendo2 = false;
			    }
			    if (linea.equals("d")) {
				// Salir del programa
				corriendo3 = false;
				lenguaje = false;
				corriendo = false;
				corriendo2 = false;
			    }

			    // Error en la seleccion, pausa y regreso al menu
			    if (!(linea.equals("a") || linea.equals("b")
				    || linea.equals("c") || linea.equals("d"))) {
				System.err
					.println("Error. Please, input a, b, c , d");
				try {
				    Thread.sleep(2000);
				} catch (InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				corriendo2 = false;
			    }
			} while (corriendo2);

		    }
		}
		// Error en la seleccion, pausa y regreso al menu
		if (!(idioma.equals("a") || idioma.equals("b")
			|| idioma.equals("Español") || idioma.equals("English"))) {
		    System.err
			    .println("Error. Por favor, selecciona el idioma");
		    System.err.println("Please, select your language");
		    try {
			Thread.sleep(2000);
		    } catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		    }
		    lenguaje = false;
		}

	    } while (lenguaje == true);
	}
    }
}
