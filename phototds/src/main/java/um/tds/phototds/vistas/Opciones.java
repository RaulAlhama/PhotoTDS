package um.tds.phototds.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Opciones {

	private JFrame opciones;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opciones window = new Opciones();
					window.opciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Opciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		opciones = new JFrame();
		opciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		opciones.setBounds(100, 100, 314, 164);
		JPanel panel = new JPanel();
		opciones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
	
		JButton btnGenerarPDF = new JButton("GenerarPDF");
		btnGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
			}
		});
		panel.add(btnGenerarPDF);

		JButton btnFotosConMas = new JButton("Fotos con mas MG");
		panel.add(btnFotosConMas);

		JButton btnGenerarexcel = new JButton("GenerarExcel");
		panel.add(btnGenerarexcel);

		JButton btnPremium = new JButton("Premium");
		panel.add(btnPremium);
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		
		panel.add(btnCerrarSesin);
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);

	}

}
