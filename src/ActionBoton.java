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
		int numMinas;

		System.out.println("-> Ejecutada acción (" + posI + ", " + posJ + ")");

		if (!ventana.juego.abrirCasilla(posI, posJ)) {
			// Hemos pisado una mina
			ventana.mostrarFinJuego(true); // Termina el juego
		} else {
			
			ventana.mostrarNumMinasAlrededor(posI, posJ); // Mostramos minas alrededor
			ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (ventana.juego.esFinJuego()) // Si es el final del juego
				ventana.mostrarFinJuego(false); // Mostramos final del juego, ¡¡ HEMOS GANADO !!
			else {
				// Obtenemos el número de minas
				numMinas = ventana.juego.getMinasAlrededor(posI, posJ);
				
				// En caso de que tengamos 0 minas en esta casilla, ejecutamos el onClick de las casillas adyacentes
				if (numMinas == 0) {	 
					
					if (i > 0) {
						if (!ventana.casillaAbierta[posI-1][posJ])
							ventana.botonesJuego[posI-1][posJ].doClick();					
					} if (i > 0 && j > 0) {
						if (!ventana.casillaAbierta[posI-1][posJ-1])
							ventana.botonesJuego[posI-1][posJ-1].doClick();
					}
					if (j > 0) {
						if (!ventana.casillaAbierta[posI][posJ-1])
							ventana.botonesJuego[posI][posJ-1].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO-1 && j>0) {
						if (!ventana.casillaAbierta[posI+1][posJ-1])
							ventana.botonesJuego[posI+1][posJ-1].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO-1) {
						if (!ventana.casillaAbierta[posI+1][posJ])
							ventana.botonesJuego[posI+1][posJ].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO-1 && j < ventana.juego.LADO_TABLERO-1) {
						if (!ventana.casillaAbierta[posI+1][posJ+1])
							ventana.botonesJuego[posI+1][posJ+1].doClick();
					}
					if (j < ventana.juego.LADO_TABLERO-1) {
						if (!ventana.casillaAbierta[posI][posJ+1])
							ventana.botonesJuego[posI][posJ+1].doClick();
					}
					if (i > 0 && j < ventana.juego.LADO_TABLERO-1) {
						if (!ventana.casillaAbierta[posI-1][posJ+1])
							ventana.botonesJuego[posI-1][posJ+1].doClick();
					}
				}
			}
		}
	}

}
