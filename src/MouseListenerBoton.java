import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerBoton implements MouseListener{

	private VentanaPrincipal ventana;
	private int i;
	private int j;
	
	public MouseListenerBoton(VentanaPrincipal ventana, int i, int j) {
		this.ventana = ventana;
		this.i = i;
		this.j = j;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Comprobamos que se ha pulsado el botón derecho (BUTTON3)
		if (e.getButton() == MouseEvent.BUTTON3) {
			
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
