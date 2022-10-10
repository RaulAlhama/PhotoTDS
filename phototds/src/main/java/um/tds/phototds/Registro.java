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
import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;

public class Registro {

	private JFrame frame;
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
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 579, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelDatos = new JPanel();
		frame.getContentPane().add(panelDatos, BorderLayout.CENTER);
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
		txtContrasea.setText("Contrase�a");
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
		
		JLabel lblFotoUsuario = new JLabel("A�adir foto del usuario (opcional)");
		panelFotoUsuario.add(lblFotoUsuario);
		
		JButton btnFotoUsuario = new JButton("+");
		panelFotoUsuario.add(btnFotoUsuario);
		
		JPanel panelPresentacion = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelPresentacion.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelDatos.add(panelPresentacion);
		
		JLabel lblPresentacion = new JLabel("A�adir presentacion (opcional)");
		panelPresentacion.add(lblPresentacion);
		
		JButton btnPresentacion = new JButton(". . .");
		panelPresentacion.add(btnPresentacion);
		
		JPanel panelTitulo = new JPanel();
		frame.getContentPane().add(panelTitulo, BorderLayout.NORTH);
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
		
		JLabel lblDescripcion = new JLabel("Si te registras podr�s compartir fotos y ver las fotos de tus amigos");
		lblDescripcion.setFont(new Font("Verdana", Font.PLAIN, 15));
		panelDescripcion.add(lblDescripcion);
		
		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnOK = new JButton("OK");
		panelBotones.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		panelBotones.add(btnCancel);
	}

}
