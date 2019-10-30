import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas. La clase solamente 
 * <b>controlar� qu� hacen los botones cuando se pulsa el bot�n derecho</b>. A�adir� la 
 * funcionalidad para <b>advertir al jugador que en un bot�n hay una mina</b>, <b>inhabilitando
 * el bot�n pulsado y marc�ndolo</b>. 
 * @author Sergio Garc�a Hern�ndez
 * @see MouseListener
 */
public class MouseListenerBoton implements MouseListener{

	private VentanaPrincipal ventana;
	private int i;
	private int j;
	
	/**
	 * Constructor de la clase MouseListenerBoton
	 * @param ventana Instancia de la ventana del juego.
	 * @param i Posici�n i del bot�n pulsado.
	 * @param j Posici�n j del bot�n pulsado.
	 */
	public MouseListenerBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 * M�todo que captar� la pulsaci�n del bot�n del rat�n. Solamente actuar� en caso
	 * de que se haya pulsado el bot�n derecho del rat�n. En ese caso cambiar� el texto
	 * del bot�n a M (indicando que hay una mina) y cambiar� el color del bot�n a rojo.
	 * Tambi�n se inhabilitar� el uso del bot�n. En el caso de que ya est� marcado, 
	 * se revertir� al estado normal del bot�n.
	 * @param e Evento del bot�n pulsado.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Comprobamos que se ha pulsado el bot�n derecho (BUTTON3)
		if (e.getButton() == MouseEvent.BUTTON3) {
			JButton boton = ((JButton)e.getSource());	// Obtenemos el bot�n.
			
			if (boton.getText().equals("-")) {	// Si el bot�n NO EST� EN ESTADO DE WARNING
				boton.setText("");
				boton.setBackground(Color.RED);
				boton.setIcon(new ImageIcon("iconos/bandera.png"));
				boton.setEnabled(false);
			} else {	// El bot�n EST� EN ESTADO DE WARNING
				boton.setText("-");
				boton.setIcon(null);
				boton.setBackground(new JButton().getBackground());
				boton.setEnabled(true);
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// No hace nada		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// No hace nada		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// No hace nada		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// No hace nada		
	}

}
