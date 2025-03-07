import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendr� que poder acceder a la ventana principal. Se puede lograr
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
	 * @param i       Posici�n i del bot�n pulsado
	 * @param j       Posici�n j del bot�n pulsado
	 */
	public ActionBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}

	/**
	 * Acci�n que ocurrir� cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int numMinas;
		
		if (!ventana.juego.abrirCasilla(i, j)) {
			// Hemos pisado una mina
			
			// Paramos el crono
			ventana.panelCrono.parar();
			
			// Suena el Abuelo Simpson gritando "�� AHH !! �� LA MUERTE !! "
			Sonido sonidoAbuelo = new Sonido("sonidos/ah_la_muerte.mp3");
			sonidoAbuelo.start();

//			ventana.labelImagen.setIcon(new ImageIcon("iconos/ataud.png"));
			ventana.mostrarFinJuego(true); // Termina el juego
			
		} else {

			ventana.mostrarNumMinasAlrededor(i, j); // Mostramos minas alrededor
			ventana.actualizarPuntuacion(); // Actualizamos la puntuaci�n
			ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (ventana.juego.esFinJuego()) { // Si es el final del juego
				
				// Suena el Sr. Burns diciendo "Excelente..."
				Sonido sonidoAbuelo = new Sonido("sonidos/excelente.mp3");
				sonidoAbuelo.start();
				
				ventana.mostrarFinJuego(false); // Mostramos final del juego, �� HEMOS GANADO !!
			} else {
				// Obtenemos el n�mero de minas
				numMinas = ventana.juego.getMinasAlrededor(i, j);

				// En caso de que tengamos 0 minas en esta casilla, ejecutamos el onClick de las
				// casillas adyacentes
				if (numMinas == 0) {

					if (i > 0) {
						if (!ventana.casillaAbierta[i - 1][j])
							ventana.botonesJuego[i - 1][j].doClick();
					}
					if (i > 0 && j > 0) {
						if (!ventana.casillaAbierta[i - 1][j - 1])
							ventana.botonesJuego[i - 1][j - 1].doClick();
					}
					if (j > 0) {
						if (!ventana.casillaAbierta[i][j - 1])
							ventana.botonesJuego[i][j - 1].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO - 1 && j > 0) {
						if (!ventana.casillaAbierta[i + 1][j - 1])
							ventana.botonesJuego[i + 1][j - 1].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO - 1) {
						if (!ventana.casillaAbierta[i + 1][j])
							ventana.botonesJuego[i + 1][j].doClick();
					}
					if (i < ventana.juego.LADO_TABLERO - 1 && j < ventana.juego.LADO_TABLERO - 1) {
						if (!ventana.casillaAbierta[i + 1][j + 1])
							ventana.botonesJuego[i + 1][j + 1].doClick();
					}
					if (j < ventana.juego.LADO_TABLERO - 1) {
						if (!ventana.casillaAbierta[i][j + 1])
							ventana.botonesJuego[i][j + 1].doClick();
					}
					if (i > 0 && j < ventana.juego.LADO_TABLERO - 1) {
						if (!ventana.casillaAbierta[i - 1][j + 1])
							ventana.botonesJuego[i - 1][j + 1].doClick();
					}
				}
			}
		}
	}

}
