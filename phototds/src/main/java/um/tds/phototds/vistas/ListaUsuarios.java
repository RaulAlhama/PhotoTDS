package um.tds.phototds.vistas;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Usuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaUsuarios {

	private JFrame frame;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaUsuarios window = new ListaUsuarios();
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
	public ListaUsuarios() {
		controlador = Controlador.getUnicaInstancia();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 342, 324);
		frame.setTitle("Lista de Usuarios");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelCentral = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panelCentral);
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));

		if (!controlador.obtenerUsuarios().isEmpty()) {
			for (Usuario usu : controlador.obtenerUsuarios()) {

				JPanel panel = new JPanel();
				panelCentral.add(panel);
				panel.setLayout(new GridLayout(1, 0, 0, 0));


				Image imgPanel = new ImageIcon(usu.getImagenPath()).getImage();
				JLabel lblUsuario = new JLabel();
				lblUsuario.setIcon(new ImageIcon(imgPanel.getScaledInstance(120, 80, Image.SCALE_SMOOTH)));
				panel.add(lblUsuario);
				
				JLabel lblNombreusuario = new JLabel(usu.getUsername());
				panel.add(lblNombreusuario);
				
				panel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						System.out.println(usu.getUsername());
					}
				});
			}
		}
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

}
