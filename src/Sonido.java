import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Crea un nuevo hilo y lo ejecuta para que, a la vez que se ejecuta el sonido, continúe el juego.
 * Esto provoca que el juego no se pare mientras se reproduce el sonido.
 * @author Sergio García Hernández
 */
public class Sonido extends Thread{

	/** Guarda la dirección relativa donde está almacenado el sonido. */
	private String direccionSonido;
	
	/**
	 * Constructor parametrizado. 
	 * @param direccionSonido Dirección relativa donde está almacenado el sonido.
	 */
	public Sonido(String direccionSonido) {
		this.direccionSonido = direccionSonido;
	}
	
	/**
	 * Ejecuta el sonido. Comienza la ejecución del hilo.
	 */
	public void run () {
		Player apl;
		try {
			apl = new Player(new FileInputStream(direccionSonido));
			apl.play();
		} catch (FileNotFoundException | JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
