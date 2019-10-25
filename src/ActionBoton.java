import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{

	private VentanaPrincipal ventana;
	private int i;
	private int j;
	
	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		//TODO
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 * Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		// Si no hay una mina
		if (this.ventana.juego.abrirCasilla(i, j)) {
			
			this.ventana.mostrarNumMinasAlrededor(i, j);	// Mostramos minas alrededor
			this.ventana.actualizarPuntuacion();			// Actualizamos la puntuación
			this.ventana.refrescarPantalla();				// Refrescamos la pantalla
			
			if (this.ventana.juego.esFinJuego())	// Si es el final del juego
				this.ventana.mostrarFinJuego(false);	// Mostramos final del juego, ¡¡ HEMOS GANADO !!
			
		} else {	// Si hemos pulsado en una mina
			
			this.ventana.mostrarFinJuego(true);			// Termina el juego
			
		}
	}

}
