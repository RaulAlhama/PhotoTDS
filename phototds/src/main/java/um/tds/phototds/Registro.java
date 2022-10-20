package um.tds.phototds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro {

	private JFrame frameRegistro;
	private JTextField textEmail;
	private JTextField textNombre;
	private JTextField textUsuario;
	private JTextField txtContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.frameRegistro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
	}
	
	public void mostrarVentana() {
		frameRegistro.setLocationRelativeTo(null); //NULL -> Se posiciona en el centro de la pantalla
		frameRegistro.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRegistro = new JFrame();
		frameRegistro.setBounds(100, 100, 579, 396);
		frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDatos = new JPanel();
		frameRegistro.getContentPane().add(panelDatos, BorderLayout.CENTER);
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		
		JPanel panelEmail = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelEmail.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelEmail);
		
		textEmail = new JTextField();
		textEmail.setText("Email");
		panelEmail.add(textEmail);
		textEmail.setColumns(40);
		
		JPanel panelNombre = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panelNombre.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelNombre);
		
		textNombre = new JTextField();
		textNombre.setText("Nombre completo");
		panelNombre.add(textNombre);
		textNombre.setColumns(40);
		
		JPanel panelUsuario = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panelUsuario.getLayout();
		flowLayout_7.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setText("Nombre de usuario");
		panelUsuario.add(textUsuario);
		textUsuario.setColumns(40);
		
		JPanel panelClave = new JPanel();
		FlowLayout flowLayout_8 = (FlowLayout) panelClave.getLayout();
		flowLayout_8.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelClave);
		
		txtContrasea = new JTextField();
		txtContrasea.setText("Contraseña");
		panelClave.add(txtContrasea);
		txtContrasea.setColumns(40);
		
		JPanel panelFechaNacimiento = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFechaNacimiento.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelFechaNacimiento);
		
		JLabel lblFecha = new JLabel("Fecha de Nacimiento");
		panelFechaNacimiento.add(lblFecha);
		
		JDateChooser dateChooser = new JDateChooser();
		panelFechaNacimiento.add(dateChooser);
		
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
				JFrame frame = new JFrame();
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
				Login2 ventanaLogin = new Login2();
				ventanaLogin.mostrarVentana();
				frameRegistro.dispose();
			}
		});
		panelBotones.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login2 ventanaLogin = new Login2();
				ventanaLogin.mostrarVentana();
				frameRegistro.dispose();
			}
		});
		panelBotones.add(btnCancel);
	}

}
