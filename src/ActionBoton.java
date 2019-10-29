import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

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
		int numMinas;
		
		if (!ventana.juego.abrirCasilla(i, j)) {
			// Hemos pisado una mina
			
			Player apl;
			try {
				apl = new Player(new FileInputStream("sonidos/a_fregar.mp3"));
				apl.play();
			} catch (FileNotFoundException | JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			ventana.mostrarFinJuego(true); // Termina el juego
			
		} else {

			ventana.mostrarNumMinasAlrededor(i, j); // Mostramos minas alrededor
			ventana.actualizarPuntuacion(); // Actualizamos la puntuación
			ventana.refrescarPantalla(); // Refrescamos la pantalla

			if (ventana.juego.esFinJuego()) // Si es el final del juego
				ventana.mostrarFinJuego(false); // Mostramos final del juego, ¡¡ HEMOS GANADO !!
			else {
				// Obtenemos el número de minas
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
