import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendrá que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener {

	private VentanaPrincipal ventana;
	private int i;
	private int j;

	/**
	 * Constructor por defecto del ActionBoton.
	 * 
	 * @param ventana Ventana del juego
	 * @param i       Posición i del botón pulsado
	 * @param j       Posición j del botón pulsado
	 */
	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}

	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("\n--> PULSADA CASILLA (" + i + ", " + j + ") <--\n");
		ejecutarAccion(i, j);
		System.out.println("\n--> FIN ACCIÓN (" + i + ", " + j + ") <--\n");
	}

	public void ejecutarAccion(int posI, int posJ) {
		int numMinas = ventana.juego.getMinasAlrededor(posI, posJ);

		System.out.println("-> Ejecutada acción (" + posI + ", " + posJ + "). NumMinas: " + numMinas);

		// Si el número indicado en la casilla es 0, abrirá los botones de alrededor.
		if (numMinas == 0) {

			// Abrimos todas las casillas alrededor, ya que no hay una mina en ninguna de
			// ellas.
			ventana.abrirCasillaCero(posI, posJ);

			ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (ventana.juego.esFinJuego()) // Si es el final del juego
				ventana.mostrarFinJuego(false); // Mostramos final del juego, ¡¡ HEMOS GANADO !!
			else {
				// Ahora expandimos hacia todos los lados (siempre que sea posible)
				if (posI > 0) {
					if (ventana.juego.getMinasAlrededor(posI - 1, posJ) == 0 
							&& !ventana.estanAbiertasTodasAlrededor(posI-1, posJ))
						ejecutarAccion(posI - 1, posJ); // Hacia arriba
				}
				if (posI < ventana.juego.LADO_TABLERO - 1) {
					if (ventana.juego.getMinasAlrededor(posI + 1, posJ) == 0 
							&& !ventana.estanAbiertasTodasAlrededor(posI+1, posJ))
						ejecutarAccion(posI + 1, posJ); // Hacia abajo
				}
				if (posJ > 0) {
					if (ventana.juego.getMinasAlrededor(posI, posJ - 1) == 0 
							&& !ventana.estanAbiertasTodasAlrededor(posI, posJ-1))
						ejecutarAccion(posI, posJ - 1); // Hacia izquierda
				}
				if (posJ < ventana.juego.LADO_TABLERO - 1) {
					if (ventana.juego.getMinasAlrededor(posI, posJ + 1) == 0 
							&& !ventana.estanAbiertasTodasAlrededor(posI, posJ+1))
						ejecutarAccion(posI, posJ + 1); // Hacia derecha
				}
			}

		} else if (numMinas == -1) { // Hemos pisado una mina

			ventana.mostrarFinJuego(true); // Termina el juego

		} else { // Hemos pulsado una casilla sin mina, pero no hay que expandir

			ventana.mostrarNumMinasAlrededor(posI, posJ); // Mostramos minas alrededor
			ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (ventana.juego.esFinJuego()) // Si es el final del juego
				ventana.mostrarFinJuego(false); // Mostramos final del juego, ¡¡ HEMOS GANADO !!
		}
	}

}
