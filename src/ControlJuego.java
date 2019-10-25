import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posici�n guarda el n�mero -1
 * Si no hay una mina, se guarda cu�ntas minas hay alrededor.
 * Almacena la puntuaci�n de la partida
 * @author jesusredondogarcia
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
		
		depurarTablero();
	}
	
	
	/**
	 * M�todo para generar un nuevo tablero de partida:
	 * @pre: La estructura tablero debe existir. 
	 * @post: Al final el tablero se habr� inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cu�ntas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		int minasColocadas = 0;	// Contador de minas colocadas en el tablero
		int x, y;	// Nos sirve para colocar las minas en el tablero

		//TODO: Repartir minas e inicializar puntaci�n. Si hubiese un tablero anterior, lo pongo todo a cero para inicializarlo.
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j] = 0;	// Inicializo el tablero a cero
			}
		}
		
		/*
		 *  Una vez tengo el tablero inicializado, sin minas, coloco las minas. Genero una dupla (x, y) aleatoriamente y, si
		 *  no contiene ninguna mina, la coloco en ella y decremento el contador de minas colocadas.
		 */
		while (minasColocadas < MINAS_INICIALES) {
			x = (int) (Math.random()*LADO_TABLERO);
			y = (int) (Math.random()*LADO_TABLERO);
			
			// Si no hay mina en la posici�n (x, y), pongo una mina y aumento el contador
			if (tablero[x][y] == 0) {		
				tablero[x][y] = MINA;
				minasColocadas++;
			}
		}
		
		// Al final del m�todo hay que guardar el n�mero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
	}
	
	/**
	 * C�lculo de las minas adjuntas: 
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdr�n LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdr�n 0.
	 * @param i: posici�n vertical de la casilla a rellenar
	 * @param j: posici�n horizontal de la casilla a rellenar
	 * @return : El n�mero de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j){
		int contadorMinas = 0;	// Contador de minas
		// Guarda las posiciones de inicio y fin de comprobaci�n.
		int inicioI = (i - 1),
				finI = (i + 1),
				inicioJ = (j - 1),
				finJ = (j + 1);
		
		for (int k = inicioI; k <= finI; k++) {
			for (int l = inicioJ; l <= finJ; l++) {
				try {
					if (tablero[k][l] == MINA)
						contadorMinas++;
				} catch(IndexOutOfBoundsException e) {
					/*
					 *  Captura la excepci�n en caso de que te salgas del tablero para que no se salga.
					 *  No hacemos nada en caso de que ocurra esto.
					 */
				}
			}
		}
		
		return contadorMinas;
	}
	
	/**
	 * M�todo que nos permite comprobar si ha sido explotada una mina o no. En caso de no explotar una mina, suma puntos.
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posici�n verticalmente de la casilla a abrir
	 * @param j: posici�n horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		if (tablero[i][j] == MINA) {
			return false;	// Ha explotado una mina
		} else {
			puntuacion++;
			return true;	// No ha explotado una mina
		}
	}
	
	
	
	/**
	 * M�todo que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		// Se cumple cuando la puntuaci�n es igual al n�mero de casillas totales mostradas
		return ((LADO_TABLERO*LADO_TABLERO)-MINAS_INICIALES) == puntuacion;
	}
	
	
	/**
	 * M�todo que pinta por pantalla toda la informaci�n del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: "+puntuacion);
	}

	/**
	 * M�todo que se utiliza para obtener las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, s�mplemente consultarlo
	 * @param i : posici�n vertical de la celda.
	 * @param j : posici�n horizontal de la cela.
	 * @return Un entero que representa el n�mero de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * M�todo que devuelve la puntuaci�n actual
	 * @return Un entero con la puntuaci�n actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
