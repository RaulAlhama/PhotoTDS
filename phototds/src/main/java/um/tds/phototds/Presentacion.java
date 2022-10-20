package um.tds.phototds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Presentacion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion window = new Presentacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Presentacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		panelBotones.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		panelBotones.add(btnCancel);
		
		JLabel lblTitulo = new JLabel("Escribe tu presentación (máximo 200 caracteres)");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblTitulo, BorderLayout.NORTH);
		
	}

}
