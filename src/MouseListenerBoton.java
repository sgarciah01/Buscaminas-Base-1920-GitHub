import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas. La clase solamente 
 * <b>controlará qué hacen los botones cuando se pulsa el botón derecho</b>. Añadirá la 
 * funcionalidad para <b>advertir al jugador que en un botón hay una mina</b>, <b>inhabilitando
 * el botón pulsado y marcándolo</b>. 
 * @author Sergio García Hernández
 * @see MouseListener
 */
public class MouseListenerBoton implements MouseListener{

	private VentanaPrincipal ventana;
	private int i;
	private int j;
	
	/**
	 * Constructor de la clase MouseListenerBoton
	 * @param ventana Instancia de la ventana del juego.
	 * @param i Posición i del botón pulsado.
	 * @param j Posición j del botón pulsado.
	 */
	public MouseListenerBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	/**
	 * Método que captará la pulsación del botón del ratón. Solamente actuará en caso
	 * de que se haya pulsado el botón derecho del ratón. En ese caso cambiará el texto
	 * del botón a M (indicando que hay una mina) y cambiará el color del botón a rojo.
	 * También se inhabilitará el uso del botón. En el caso de que ya esté marcado, 
	 * se revertirá al estado normal del botón.
	 * @param e Evento del botón pulsado.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Comprobamos que se ha pulsado el botón derecho (BUTTON3)
		if (e.getButton() == MouseEvent.BUTTON3) {
			JButton boton = ((JButton)e.getSource());	// Obtenemos el botón.
			
			if (boton.getText().equals("-")) {	// Si el botón NO ESTÁ EN ESTADO DE WARNING
				boton.setText("");
				boton.setBackground(Color.RED);
				boton.setIcon(new ImageIcon("iconos/bandera.png"));
				boton.setEnabled(false);
			} else {	// El botón ESTÁ EN ESTADO DE WARNING
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
