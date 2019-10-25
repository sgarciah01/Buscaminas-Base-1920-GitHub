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
		ejecutarAccion(i, j);
	}

	public void ejecutarAccion(int posI, int posJ) {
		int numMinas = this.ventana.juego.getMinasAlrededor(posI, posJ);

		// Si el número indicado en la casilla es 0, abrirá los botones de alrededor.
		if (numMinas == 0) {

			this.ventana.mostrarNumMinasAlrededor(posI, posJ); // Mostramos minas alrededor
			this.ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			
			// Ahora expandimos hacia todos los lados (siempre que sea posible)
			if (posI > 0)	{
				if (!this.ventana.casillasAbiertas[posI-1][posJ])
					ejecutarAccion(posI-1, posJ);		// Hacia la izquierda
			}
			if (posI < this.ventana.juego.LADO_TABLERO) {
				if (!this.ventana.casillasAbiertas[posI+1][posJ])
					ejecutarAccion(posI+1, posJ);		// Hacia la derecha
			}
			if (posJ > 0) {
				if (!this.ventana.casillasAbiertas[posI][posJ-1])
					ejecutarAccion(posI, posJ-1);		// Hacia arriba
			}
			if (posJ < this.ventana.juego.LADO_TABLERO) {	
				if (!this.ventana.casillasAbiertas[posI][posJ+1])
					ejecutarAccion(posI, posJ+1);		// Hacia abajo
			}

		} else if (numMinas == -1) { // Hemos pisado una mina

			this.ventana.mostrarFinJuego(true); // Termina el juego

		} else { // Hemos pulsado una casilla sin mina, pero no hay que expandir
			this.ventana.mostrarNumMinasAlrededor(posI, posJ); // Mostramos minas alrededor
			this.ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			this.ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (this.ventana.juego.esFinJuego()) // Si es el final del juego
				this.ventana.mostrarFinJuego(false); // Mostramos final del juego, ¡¡ HEMOS GANADO !!
		}
	}

}
