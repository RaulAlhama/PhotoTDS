package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;

import um.tds.phototds.controlador.Controlador;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registro {

	private JFrame frameRegistro;
	private JTextField textEmail;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JPasswordField passwordClave;
	private JDateChooser textFecha;
	private JPasswordField passwordClaveChk;
	private JLabel lblError;

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
		lblError.setVisible(false);
		lblError.setForeground(Color.RED);
	}

	public void mostrarVentana() {
		frameRegistro.setLocationRelativeTo(null); // NULL -> Se posiciona en el centro de la pantalla
		frameRegistro.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRegistro = new JFrame();
		frameRegistro.setBounds(100, 100, 594, 407);
		frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelDatos = new JPanel();
		frameRegistro.getContentPane().add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

		JPanel panelEmail = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelEmail.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelEmail);

		textEmail = new JTextField();
		textEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textEmail.getText().equals("Email")){
				textEmail.setText("");
				}
			}
		});
		textEmail.setText("Email");
		panelEmail.add(textEmail);
		textEmail.setColumns(42);

		JPanel panelNombre = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panelNombre.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelNombre);

		textNombre = new JTextField();
		textNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textNombre.getText().equals("Nombre completo")) textNombre.setText("");
			}
		});
		textNombre.setText("Nombre completo");
		panelNombre.add(textNombre);
		textNombre.setColumns(42);

		JPanel panelUsuario = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panelUsuario.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelUsuario);

		textUsuario = new JTextField();
		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textUsuario.getText().equals("Nombre de usuario")) textUsuario.setText("");
			}
		});
		textUsuario.setText("Nombre de usuario");
		panelUsuario.add(textUsuario);
		textUsuario.setColumns(42);

		JPanel panelClave = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panelClave.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelClave);
		
		JLabel lblClave = new JLabel("Clave:");
		panelClave.add(lblClave);

		passwordClave = new JPasswordField();
		passwordClave.setColumns(15);
		panelClave.add(passwordClave);
		
		JLabel lblRepetirClave = new JLabel("Repetir Clave:");
		panelClave.add(lblRepetirClave);
		
		passwordClaveChk = new JPasswordField();
		passwordClaveChk.setColumns(15);
		panelClave.add(passwordClaveChk);
		
		lblError = new JLabel("");
		panelDatos.add(lblError);
		

		JPanel panelFechaNacimiento = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFechaNacimiento.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelFechaNacimiento);

		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		panelFechaNacimiento.add(lblFecha);

		textFecha = new JDateChooser();
		panelFechaNacimiento.add(textFecha);

		JPanel panelFotoUsuario = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelFotoUsuario.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelFotoUsuario);

		JLabel lblFotoUsuario = new JLabel("Añadir foto del usuario (opcional)");
		panelFotoUsuario.add(lblFotoUsuario);

		JButton btnFotoUsuario = new JButton("+");
		btnFotoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser j = new JFileChooser();
				j.showSaveDialog(null);
			}
		});
		panelFotoUsuario.add(btnFotoUsuario);

		JPanel panelPresentacion = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelPresentacion.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelPresentacion);

		JLabel lblPresentacion = new JLabel("Añadir presentacion (opcional)");
		panelPresentacion.add(lblPresentacion);

		JButton btnPresentacion = new JButton(". . .");
		btnPresentacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFrame frame = new JFrame();
				frame.setBounds(100, 100, 450, 300);
				// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				final JTextArea textArea = new JTextArea();
				frame.getContentPane().add(textArea, BorderLayout.CENTER);

				JPanel panelBotones = new JPanel();
				frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(textArea.getText());
						frame.dispose();
					}
				});
				panelBotones.add(btnOk);

				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.dispose();

					}
				});
				panelBotones.add(btnCancel);

				JLabel lblTitulo = new JLabel("Escribe tu presentación (máximo 200 caracteres)");
				lblTitulo.setFont(new Font("Verdana", Font.BOLD, 13));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				frame.getContentPane().add(lblTitulo, BorderLayout.NORTH);

				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
		panelPresentacion.add(btnPresentacion);

		JPanel panelTitulo = new JPanel();
		frameRegistro.getContentPane().add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));

		JPanel panelTexto = new JPanel();
		panelTitulo.add(panelTexto);
		panelTexto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPhotoTDS = new JLabel("PhotoTDS");
		lblPhotoTDS.setFont(new Font("Verdana", Font.BOLD, 22));
		panelTexto.add(lblPhotoTDS);

		JPanel panelDescripcion = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panelDescripcion.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panelTitulo.add(panelDescripcion);

		JLabel lblDescripcion = new JLabel("Si te registras podrás compartir fotos y ver las fotos de tus amigos");
		lblDescripcion.setFont(new Font("Verdana", Font.PLAIN, 15));
		panelDescripcion.add(lblDescripcion);

		JPanel panelBotones = new JPanel();
		frameRegistro.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean OK = false;
				OK = validarRegistro();
				if (!OK) {
					JOptionPane.showMessageDialog(frameRegistro, "Algunos campos obligatorios están vacíos",
							"Error en el registro", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Controlador.getUnicaInstancia().registrarUsuario(textNombre.getText(),
								textEmail.getText(), textUsuario.getText(), String.valueOf(passwordClave.getPassword()), textFecha.getDate().toString());
						JOptionPane.showMessageDialog(frameRegistro, "Se ha registrado correctamente",
								"Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
						Login ventanaLogin = new Login();
						ventanaLogin.mostrarVentana();
						frameRegistro.dispose();
					} catch (Exception e2){
						JOptionPane.showMessageDialog(frameRegistro, e2.getMessage(),
								"Error en el registro", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
			}
		});
		panelBotones.add(btnOK);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login ventanaLogin = new Login();
				ventanaLogin.mostrarVentana();
				frameRegistro.dispose();
			}
		});
		panelBotones.add(btnCancel);
	}

	private boolean validarRegistro() {
		boolean verificar = true;

		if (textNombre.getText().isEmpty()) {
			textNombre.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}
		
		if(textEmail.getText().isEmpty()) {
			textEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}

		if (textFecha.getDate() == null) {
			verificar = false;
		}

		if (textUsuario.getText().isEmpty()) {
			textUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}

		if (passwordClave.getPassword().length == 0) {
			passwordClave.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}
		
		if(passwordClaveChk.getPassword().length == 0) {
			passwordClaveChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}
		
		if(!(String.valueOf(passwordClave.getPassword())
				.equals(String.valueOf(passwordClaveChk.getPassword())))) {
			lblError.setText("Las Claves no coinciden");
			passwordClave.setBorder(BorderFactory.createLineBorder(Color.RED));
			lblError.setVisible(true);
			passwordClaveChk.setBorder(BorderFactory.createLineBorder(Color.RED));
			verificar = false;
		}
		frameRegistro.revalidate();
		return verificar;
	}

}
