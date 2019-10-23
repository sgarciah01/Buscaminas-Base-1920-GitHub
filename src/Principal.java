import java.awt.EventQueue;

/**
 * Clase principal
 * @author oscarlaguna
 *
 */
public class Principal {

	/**
	 * M�todo main
	 * @param args : Cadenas de par�metros del main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana = new VentanaPrincipal();
					ventana.inicializar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
