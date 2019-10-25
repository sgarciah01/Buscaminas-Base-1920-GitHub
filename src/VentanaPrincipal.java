import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaPrincipal {

	// La ventana principal, en este caso, guarda todos los componentes:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;

	// Todos los botones se meten en un panel independiente.
	// Hacemos esto para que podamos cambiar despu�s los componentes por otros
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Correspondencia de colores para las minas:
	Color correspondenciaColores[] = { Color.BLACK, Color.BLUE, new Color(45, 87, 44), new Color(216, 75, 32), Color.RED, 
			Color.RED, Color.RED, Color.RED, Color.RED, Color.RED };

	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	// LA VENTANA GUARDA UN CONTROL DE JUEGO:
	ControlJuego juego;

	// Constructor, marca el tama�o y el cierre del frame
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
	}

	// Inicializa todos los componentes del frame
	public void inicializarComponentes() {

		// Definimos el layout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10));

		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos los componentes:
		// AZUL
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelImagen, settings);
		// VERDE
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);
		// AMARILLO
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);
		// ROJO
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}

		// Bot�nEmpezar:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);
	}

	/**
	 * M�todo que inicializa todos los l�steners que necesita inicialmente el
	 * programa
	 */
	public void inicializarListeners() {
		
		// Inicializamos los listeners de los botones del panel.
		for (int i = 0; i < juego.LADO_TABLERO; i++) {
			for (int j = 0; j < juego.LADO_TABLERO; j++) {
				// Utilizamos la clase ActionBoton
				botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
				botonesJuego[i][j].
			}
		}
		
		// Le ponemos un ActionListener al bot�n de empezar
		botonEmpezar.addActionListener((e) -> {
			// Cuando pulsemos, aparece un JOption. Si decimos que s�, inicializamos el juego
			if (JOptionPane.showConfirmDialog(null, "�Quieres empezar un nuevo juego?", 
					"Nuevo Juego", JOptionPane.OK_OPTION) == JOptionPane.YES_OPTION) {
				
				// Eliminamos las vistas que hay en los paneles
				ponerJuegoAPunto();
				
				juego = new ControlJuego();
				//inicializar();
				refrescarPantalla();
			}
		});
	}
	
	public void ponerJuegoAPunto() {
		
		// Ponemos puntuaci�n a cero
		pantallaPuntuacion.setText("0");

		// Eliminamos los botones y los volvemos a poner
		panelJuego.removeAll();
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}
		
		panelEmpezar.removeAll();	
		botonEmpezar = new JButton("Go!");

		// Botones
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
			}
		}
		
		// A�adimos bot�n empezar
		panelEmpezar.add(botonEmpezar);
		
		// A�adimos los listeners
		inicializarListeners();
	}

	/**
	 * Pinta en la pantalla el n�mero de minas que hay alrededor de la celda Saca el
	 * bot�n que haya en la celda determinada y a�ade un JLabel centrado y no
	 * editable con el n�mero de minas alrededor. Se pinta el color del texto seg�n
	 * la siguiente correspondecia (consultar la variable correspondeciaColor): - 0
	 * : negro - 1 : cyan - 2 : verde - 3 : naranja - 4 � m�s : rojo
	 * 
	 * @param i: posici�n vertical de la celda.
	 * @param j: posici�n horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		// TODO
		int numMinas = juego.getMinasAlrededor(i, j);

		// Definimos el JLabel y le damos los valores que queremos
		JLabel label = new JLabel(String.valueOf(numMinas));
		label.setForeground(correspondenciaColores[numMinas]); // Le ponemos el color en funci�n a la correspondencia
		label.setHorizontalAlignment(JLabel.CENTER); // Centramos el texto

		// Eliminamos el bot�n y a�adimos el JLabel
		panelesJuego[i][j].remove(botonesJuego[i][j]);
		panelesJuego[i][j].add(label);
	}

	/**
	 * Muestra una ventana que indica el fin del juego
	 * 
	 * @param porExplosion : Un booleano que indica si es final del juego porque ha
	 *                     explotado una mina (true) o bien porque hemos desactivado
	 *                     todas (false)
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		// Deshabilitamos todos los botones del tablero
		for (int i = 0; i < juego.LADO_TABLERO; i++) {
			for (int j = 0; j < juego.LADO_TABLERO; j++) {
				botonesJuego[i][j].setEnabled(false);
			}
		}

		// En el caso de que haya explotado una mina, mostramos un mensaje de error.
		if (porExplosion) {
			JOptionPane.showMessageDialog(null, "�� Ha explotado una mina !!\nFin del juego...", "�� BOOM !!",
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Has localizado todas las minas.\n"
					+ "� Has ganado !", "�� GANASTE !!", JOptionPane.OK_OPTION);
		}

		// Deshabilitamos todos los botones del tablero
		for (int i = 0; i < juego.LADO_TABLERO; i++) {
			for (int j = 0; j < juego.LADO_TABLERO; j++) {
				botonesJuego[i][j].setEnabled(false);
			}
		}
	}

	/**
	 * M�todo que muestra la puntuaci�n por pantalla.
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(String.valueOf(juego.getPuntuacion()));
	}

	/**
	 * M�todo para refrescar la pantalla
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * M�todo que devuelve el control del juego de una ventana
	 * 
	 * @return un ControlJuego con el control del juego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * M�todo para inicializar el programa
	 */
	public void inicializar() {
		// IMPORTANTE, PRIMERO HACEMOS LA VENTANA VISIBLE Y LUEGO INICIALIZAMOS LOS
		// COMPONENTES.
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

}
