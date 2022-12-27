package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import um.tds.phototds.controlador.Controlador;

public class Login {

	private JFrame frameLogin;
	private JTextField txtNombreDeUsuario;
	private JPasswordField ClaveField;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	public void mostrarVentana() {
		frameLogin.setLocationRelativeTo(null);
		frameLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setBounds(100, 100, 476, 315);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setLocationRelativeTo(null); // Para que aparezca en el centro

		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new LineBorder(new Color(0, 0, 0)));
		frameLogin.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));

		JPanel panelTitulo = new JPanel();
		panelNorte.add(panelTitulo);

		JLabel lblTitulo = new JLabel("PhotoTDS");
		lblTitulo.setFont(new Font("Verdana", Font.BOLD, 26));
		panelTitulo.add(lblTitulo);

		JPanel panelSur = new JPanel();
		panelSur.setBorder(new LineBorder(new Color(192, 192, 192)));
		frameLogin.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));

		JPanel panelTexto = new JPanel();
		panelSur.add(panelTexto);

		JLabel lblCrearCuenta = new JLabel("¿No tienes una cuenta?");
		lblCrearCuenta.setToolTipText("");
		lblCrearCuenta.setForeground(new Color(255, 0, 0));
		panelTexto.add(lblCrearCuenta);

		JPanel panelCrearCuenta = new JPanel();
		panelSur.add(panelCrearCuenta);

		JButton btnCrearCuenta = new JButton("Crear una cuenta");
		btnCrearCuenta.addActionListener(new ActionListener()/* Clase anonima */ {
			public void actionPerformed(ActionEvent e) {
				Registro ventanaRegistro = new Registro();
				ventanaRegistro.mostrarVentana();
				frameLogin.dispose();

			}
			// Una interfaz que solo tiene un m�todo, es una interfaz funcional
		});
		panelCrearCuenta.add(btnCrearCuenta);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameLogin.dispose();
				System.exit(0);
			}
		});
		panelCrearCuenta.add(btnSalir);

		JPanel panelCentro = new JPanel();
		frameLogin.getContentPane().add(panelCentro, BorderLayout.CENTER);

		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentro.add(panelLogin);
		panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));

		JPanel panelUsuario = new JPanel();
		panelLogin.add(panelUsuario);
		panelUsuario.setLayout(new BorderLayout(0, 0));

		txtNombreDeUsuario = new JTextField();
		txtNombreDeUsuario.setToolTipText("");
		panelUsuario.add(txtNombreDeUsuario, BorderLayout.EAST);
		txtNombreDeUsuario.setColumns(20);

		JLabel lblUsuario = new JLabel("Usuario:");
		panelUsuario.add(lblUsuario, BorderLayout.CENTER);

		JPanel panelClave = new JPanel();
		panelLogin.add(panelClave);
		panelClave.setLayout(new BorderLayout(0, 0));

		ClaveField = new JPasswordField();
		ClaveField.setColumns(20);
		panelClave.add(ClaveField, BorderLayout.EAST);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		panelClave.add(lblClave, BorderLayout.CENTER);

		JPanel panel_Inicio = new JPanel();
		panelCentro.add(panel_Inicio);

		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean login = Controlador.getUnicaInstancia().loginUsuario(txtNombreDeUsuario.getText(),
						new String(ClaveField.getPassword()));
				if (login) {
					VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
					ventanaPrincipal.mostrarVentana();
					frameLogin.dispose();
				} else
					JOptionPane.showMessageDialog(frameLogin, "Nombre de usuario o contraseña no valido", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});
		panel_Inicio.setLayout(new BoxLayout(panel_Inicio, BoxLayout.X_AXIS));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setForeground(new Color(0, 0, 255));
		panel_Inicio.add(btnNewButton);
	}

}
