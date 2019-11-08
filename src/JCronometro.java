

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JCronometro extends JPanel implements Runnable {
	
	// Donde mostraremos el tiempo
	JLabel contador;
	
	// Guardamos el tiempo transcurrido desde el último refresco:
	double tiempoTranscurrido;
	
	// El tiempo cuando arranca el hilo
	double tiempoOriginal;
	
	// El hilo que lanzamos con este runnable
	Thread hilo = null;
	
	// Indica que estás contando el tiempo
	boolean contandoTiempo;

	public JCronometro() {
		super();	// Creación del panel
		/* TODO: 
		 * - Cambiar fondo
		 * - Incluir borde
		 * - Inicializar el contador (JLabel)
		 * - Cambiar el estilo de letra
		 */
		// Color de fondo
		setBackground(new Color(150, 250, 250));
		
		// Borde azul oscuro, ancho 3 y sin redondear
		setBorder(BorderFactory.createLineBorder(new Color(0, 180, 180), 3, false));
		
		// Inicializamos el JLabel y cambiamos el estilo de letra
		setLayout(new GridLayout(1,  1));
		
		contador = new JLabel("00:00");
		contador.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		contador.setHorizontalAlignment(JLabel.CENTER);
		
		add(contador);
		
		this.tiempoOriginal = 0;
		this.tiempoTranscurrido = 0;
		this.contandoTiempo = false;
	}
	
	/**
	 * Comienza a contar el tiempo.
	 * Si ya se está contando el tiempo, {@link #hilo} no es nulo y por lo tanto no hace nada.
	 * Si no se está contando el tiempo, {@link #hilo} debe valer nulo y habría que inicializarlo.
	 */
	public void comenzar() {
		System.out.println("Entra en COMENZAR");
		if (hilo == null) {
			System.out.println("- Inicializamos el hilo y lo lanzamos.");
			hilo = new Thread(this);
			hilo.start();
		}		
	}
	
	/**
	 * Si el cronómetro está contando, debemos pararlo, ello supone:
	 * - Dejar de ejecutar el run (evitando el {@link Thread#stop()} ya que se encuentra deprecado
	 */
	public void parar() {
		if (contandoTiempo) {
			contandoTiempo = false;
			hilo.interrupt();
		}
		
		
	}
	
	/**
	 * Resetea el valor del {@link #contador}. Si estuviese contando, solo lo pone a cero, pero sigue contando.
	 */
	public void resetear() {
		tiempoOriginal = System.nanoTime();
		contador.setText("00:00");
	}
	
	/**
	 * Mide el tiempo según va transcurriendo.
	 * Inicias el tiempo original.
	 * Marcas que estás contando el tiempo.
	 * Mientras se está contando el tiempo, se calcula el tiempo transcurrido y después se actualiza la pantalla.
	 * Al final el {@link #hilo} se pone a null.
	 */
	@Override
	public void run() {
		// Inicias el tiempo original
		tiempoOriginal = System.nanoTime();
		contandoTiempo = true;
		
		while(contandoTiempo) {
			calcularTiempoTranscurrido();
			actualizarPantalla();
			try {
				hilo.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		hilo = null;
	}

	/**
	 * Almnacena en {@link #tiempoTranscurrido} los ns que han pasado desde {@link #tiempoOriginal} hasta ahora.
	 */
	private void calcularTiempoTranscurrido() {
		tiempoTranscurrido = System.nanoTime() - tiempoOriginal;
	}
	
	/**
	 * Pinta en la pantalla {@link #contador} el tiempo en mm:ss
	 */
	private void actualizarPantalla() {
		String tiempo = "", segString, minString;
		double segundos, minutos;
		
		segundos = tiempoTranscurrido * Math.pow(10, -9);
		minutos = segundos / 60;
		
		if (segundos < 10) {
			segString = "0" + (int) segundos;
			minString = "00";			
		} else {
			if (segundos < 59) {
				segString = String.valueOf((int)segundos);
				minString = "00";
			} else {
				do {
					segundos = segundos%60;
				} while (segundos >= 60);
				
				if (segundos < 10)
					segString = "0" + String.valueOf((int)(segundos));
				else 
					segString = String.valueOf((int)(segundos));
				
				if (minutos < 10)
					minString = "0" + String.valueOf((int)minutos);
				else
					minString = String.valueOf((int)minutos);
			}
		}
		
		//System.out.println("\t\tMinuto: " + minString + "\n\t\tSegundo: " + segString);
		contador.setText(minString + ":" + segString);
	}
	
}
