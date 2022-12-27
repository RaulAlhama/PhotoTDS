package um.tds.phototds.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Opciones {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opciones window = new Opciones();
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
	public Opciones() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 314, 164);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JButton btnGenerarPDF = new JButton("GenerarPDF");
		btnGenerarPDF.setEnabled(false);
		btnGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(btnGenerarPDF);
		
		JButton btnFotosConMas = new JButton("Fotos con mas MG");
		btnFotosConMas.setEnabled(false);
		panel.add(btnFotosConMas);
		
		JButton btnGenerarexcel = new JButton("GenerarExcel");
		btnGenerarexcel.setEnabled(false);
		panel.add(btnGenerarexcel);
	
		JButton btnPremium = new JButton("Premium");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Ahora eres usuario PREMIUM",
						"¡Se han desbloqueado nuevas funciones1", JOptionPane.INFORMATION_MESSAGE);
				btnGenerarexcel.setEnabled(true);
				btnGenerarPDF.setEnabled(true);
				btnFotosConMas.setEnabled(true);
				frame.dispose();
			}
		});
		panel.add(btnPremium);
		
		frame.setVisible(true);
	}

}
