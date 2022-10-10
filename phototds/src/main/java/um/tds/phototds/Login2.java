package um.tds.phototds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login2 {

	private JFrame frame;
	private JTextField txtNombreDeUsuario;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login2 window = new Login2();
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
	public Login2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
		
		JPanel panelTitulo = new JPanel();
		panelNorte.add(panelTitulo);
		
		JLabel lblTitulo = new JLabel("PhotoTDS");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
		panelTitulo.add(lblTitulo);
		
		JPanel panelUsuario = new JPanel();
		panelNorte.add(panelUsuario);
		
		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setText("nombre de usuario o email");
		txtNombreDeUsuario.setToolTipText("");
		panelUsuario.add(txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(40);
		
		JPanel panelClave = new JPanel();
		panelNorte.add(panelClave);
		
		textField = new JTextField();
		textField.setText("************");
		panelClave.add(textField);
		textField.setColumns(40);
		
		JPanel panel_Inicio = new JPanel();
		panelNorte.add(panel_Inicio);
		
		JButton btnNewButton = new JButton("Iniciar Sesi�n");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setForeground(new Color(0, 0, 255));
		panel_Inicio.add(btnNewButton);
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(new LineBorder(new Color(192, 192, 192)));
		frame.getContentPane().add(panelCentro, BorderLayout.SOUTH);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		JPanel panelTexto = new JPanel();
		panelCentro.add(panelTexto);
		
		JLabel lblCrearCuenta = new JLabel("�No tienes una cuenta?");
		lblCrearCuenta.setToolTipText("");
		lblCrearCuenta.setForeground(new Color(255, 0, 0));
		panelTexto.add(lblCrearCuenta);
		
		JPanel panelCrearCuenta = new JPanel();
		panelCentro.add(panelCrearCuenta);
		
		JButton btnCrearCuenta = new JButton("Crear una cuenta");
		btnCrearCuenta.addActionListener(new ActionListener()/* Clase anonima */ {
			public void actionPerformed(ActionEvent e) {
				Registro ventanaRegistro = new Registro();
			}
		//Una interfaz que solo tiene un m�todo, es una interfaz funcional
		});
		panelCrearCuenta.add(btnCrearCuenta);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		panelCrearCuenta.add(btnSalir);
	}

}
