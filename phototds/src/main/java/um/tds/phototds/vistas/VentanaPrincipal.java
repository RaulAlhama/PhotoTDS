package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Foto;
import um.tds.phototds.dominio.Usuario;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Image;
import pulsador.Luz;
import pulsador.IEncendidoListener;
import java.util.EventObject;

public class VentanaPrincipal {

	private JFrame framePrincipal;
	private JTextField txtBuscador;
	private Controlador controlador;
	private JFrame opciones;
	private Usuario usuarioActual;
	private String fotosFile;
	
	// private String userName;
	private static final Color DEFAULT_BACKGROUND = new Color(102, 10, 45);
	private JPanel panelCentral;
	private JPanel panelCentro;
	private JScrollPane scrollFotos;

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		controlador = Controlador.getUnicaInstancia();
		usuarioActual = controlador.getUsuarioActual();
		// this.userName = controlador.getUsuarioActual().getUsername();
		initialize();
	}

	public void mostrarVentana() {
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePrincipal = new JFrame();
		framePrincipal.setBounds(100, 100, 668, 458);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelNorte = new JPanel();
		framePrincipal.getContentPane().add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 0, 0, 50, 0, 0, 0, 0, 0, 0, 30, 50, 0, 0, 0, 0 };
		gbl_panelNorte.rowHeights = new int[] { 10, 0, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		JLabel lblPhototds = new JLabel("PhotoTDS");
		lblPhototds.setForeground(DEFAULT_BACKGROUND);
		lblPhototds.setFont(new Font("Verdana", Font.BOLD, 18));
		GridBagConstraints gbc_lblPhototds = new GridBagConstraints();
		gbc_lblPhototds.insets = new Insets(0, 0, 0, 5);
		gbc_lblPhototds.gridx = 1;
		gbc_lblPhototds.gridy = 1;
		panelNorte.add(lblPhototds, gbc_lblPhototds);

		JButton btnAddFoto = new JButton("+");
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File fichero = chooser.getSelectedFile();
					SubirFoto frameSubir = new SubirFoto(fichero.getAbsolutePath());
					framePrincipal.dispose();
					frameSubir.mostrarVentana();

					
					//usuarioActual.setImagenPath(fichero.getAbsolutePath());
				}
			}
		});

		Luz luz = new Luz();
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject arg0) {
				if (luz.isEncendido()) {
					System.out.println("Encendido");
					JFileChooser choser = new JFileChooser();
					choser.setFileFilter(new FileNameExtensionFilter("XML", "xml"));
					int eleccion = choser.showOpenDialog(framePrincipal);
					if(eleccion == JFileChooser.APPROVE_OPTION) {
						File fichero = choser.getSelectedFile();
						fotosFile = fichero.getAbsolutePath();
						controlador.setFotosFile(fotosFile);
					}
				}
			}
		});
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 0, 5);
		gbc_luz.gridx = 2;
		gbc_luz.gridy = 1;
		luz.setColor(Color.YELLOW);
		panelNorte.add(luz, gbc_luz);

		btnAddFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddFoto.setBackground(DEFAULT_BACKGROUND);
		btnAddFoto.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAddFoto = new GridBagConstraints();
		gbc_btnAddFoto.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddFoto.gridx = 4;
		gbc_btnAddFoto.gridy = 1;
		panelNorte.add(btnAddFoto, gbc_btnAddFoto);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		GridBagConstraints gbc_txtBuscador = new GridBagConstraints();
		gbc_txtBuscador.gridwidth = 3;
		gbc_txtBuscador.insets = new Insets(0, 0, 0, 5);
		gbc_txtBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuscador.gridx = 5;
		gbc_txtBuscador.gridy = 1;
		panelNorte.add(txtBuscador, gbc_txtBuscador);
		txtBuscador.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setBackground(DEFAULT_BACKGROUND);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 8;
		gbc_btnBuscar.gridy = 1;
		panelNorte.add(btnBuscar, gbc_btnBuscar);

		JButton btnUsuario = new JButton("");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPerfil ventanaPerfil = new VentanaPerfil();
				ventanaPerfil.mostrarVentana();
				framePrincipal.dispose();
			}
		});
		btnUsuario.setBackground(DEFAULT_BACKGROUND);

		Image img = new ImageIcon(controlador.getUsuarioActual().getImagenPath()).getImage();
		btnUsuario.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_btnUsuario.gridx = 11;
		gbc_btnUsuario.gridy = 1;
		panelNorte.add(btnUsuario, gbc_btnUsuario);

		JButton btnMenu = new JButton("");
		btnMenu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				framePrincipal.disable();
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
				if (!controlador.getUsuarioActual().isPremium()) {
					btnGenerarPDF.setEnabled(false);
					btnFotosConMas.setEnabled(false);
					btnGenerarexcel.setEnabled(false);
				}
				btnPremium.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!controlador.getUsuarioActual().isPremium()) {
							controlador.hacerPremium();
							btnGenerarexcel.setEnabled(true);
							btnGenerarPDF.setEnabled(true);
							btnFotosConMas.setEnabled(true);
							opciones.dispose();
							framePrincipal.enable();
							JOptionPane.showMessageDialog(framePrincipal, "Ahora eres usuario PREMIUM", "¡Nuevas Funciones!",
									JOptionPane.INFORMATION_MESSAGE);
							
						} else {
							JOptionPane.showMessageDialog(framePrincipal, "Ya eres usuario premium!", "Premium",
									JOptionPane.INFORMATION_MESSAGE);
							opciones.dispose();
							framePrincipal.enable();
						}
					}
				});
				panel.add(btnPremium);
				JButton btnCerrarSesin = new JButton("Cerrar Sesión");
				btnCerrarSesin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Login ventanaLogin = new Login();
						ventanaLogin.mostrarVentana();
						opciones.dispose();
						framePrincipal.dispose();
					}
				});
				panel.add(btnCerrarSesin);
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						opciones.dispose();
						framePrincipal.enable();
					}
				});
				panel.add(btnCancelar);
				opciones.setLocationRelativeTo(btnMenu);
				opciones.setUndecorated(true);
				opciones.setVisible(true);

			}
		});
		btnMenu.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/list.png")));
		btnMenu.setBackground(DEFAULT_BACKGROUND);
		GridBagConstraints gbc_btnMenu = new GridBagConstraints();
		gbc_btnMenu.insets = new Insets(0, 0, 0, 5);
		gbc_btnMenu.gridx = 12;
		gbc_btnMenu.gridy = 1;
		panelNorte.add(btnMenu, gbc_btnMenu);
		
		panelCentral = new JPanel();
		BorderLayout bl_panelCentral = new BorderLayout();
		bl_panelCentral.setVgap(10);
		panelCentral.setLayout(bl_panelCentral);
		// framePrincipal.getContentPane().add(panelCentral, BorderLayout.CENTER);

		scrollFotos = new JScrollPane(panelCentral);
		panelCentral.setAutoscrolls(true);

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.LIGHT_GRAY);
		panelCentral.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 10));
		
		System.out.println(!usuarioActual.getFotos().isEmpty());
		if (!usuarioActual.getFotos().isEmpty()) {
			for(Foto foto : usuarioActual.getFotos()) {
				JPanel panelFoto = new JPanel();
				panelCentro.add(panelFoto);
				GridBagLayout gbl_panelFoto1 = new GridBagLayout();
				gbl_panelFoto1.columnWidths = new int[] { 192, 97, 97, 0, 0 };
				gbl_panelFoto1.rowHeights = new int[] { 20, 40, 0, 0, 0 };
				gbl_panelFoto1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_panelFoto1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				panelFoto.setLayout(gbl_panelFoto1);
				JLabel imagen = new JLabel("");
				imagen.setIcon(new ImageIcon(foto.getPath()));
				GridBagConstraints gbc_foto = new GridBagConstraints();
				gbc_foto.fill = GridBagConstraints.HORIZONTAL;
				gbc_foto.gridheight = 2;
				gbc_foto.anchor = GridBagConstraints.NORTH;
				gbc_foto.insets = new Insets(0, 0, 5, 5);
				gbc_foto.gridx = 0;
				gbc_foto.gridy = 1;
				panelFoto.add(imagen, gbc_foto);

				JButton button = new JButton("");
				button.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
				button.setBackground(Color.WHITE);
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 5, 5);
				gbc_button.gridx = 1;
				gbc_button.gridy = 1;
				panelFoto.add(button, gbc_button);

				JButton btnNewButton_1 = new JButton("");
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1
						.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Comentario.png")));
				GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
				gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
				gbc_btnNewButton_1.gridx = 2;
				gbc_btnNewButton_1.gridy = 1;
				panelFoto.add(btnNewButton_1, gbc_btnNewButton_1);

				JLabel lblMeGusta = new JLabel("26 Me gusta");
				GridBagConstraints gbc_lblMeGusta = new GridBagConstraints();
				gbc_lblMeGusta.insets = new Insets(0, 0, 5, 0);
				gbc_lblMeGusta.gridx = 3;
				gbc_lblMeGusta.gridy = 1;
				panelFoto.add(lblMeGusta, gbc_lblMeGusta);

				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/perfil.png")));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = 2;
				panelFoto.add(label, gbc_label);

				JLabel lblRaulgarcia = new JLabel("raul.garcia");
				GridBagConstraints gbc_lblRaulgarcia = new GridBagConstraints();
				gbc_lblRaulgarcia.insets = new Insets(0, 0, 5, 5);
				gbc_lblRaulgarcia.gridx = 2;
				gbc_lblRaulgarcia.gridy = 2;
				panelFoto.add(lblRaulgarcia, gbc_lblRaulgarcia);
			}
		}

		/*JPanel panelFoto1 = new JPanel();
		panelCentro.add(panelFoto1);
		GridBagLayout gbl_panelFoto1 = new GridBagLayout();
		gbl_panelFoto1.columnWidths = new int[] { 192, 97, 97, 0, 0 };
		gbl_panelFoto1.rowHeights = new int[] { 20, 40, 0, 0, 0 };
		gbl_panelFoto1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelFoto1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelFoto1.setLayout(gbl_panelFoto1);

		JLabel foto = new JLabel("");
		foto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/paisajeTDS.png")));
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.fill = GridBagConstraints.HORIZONTAL;
		gbc_foto.gridheight = 2;
		gbc_foto.anchor = GridBagConstraints.NORTH;
		gbc_foto.insets = new Insets(0, 0, 5, 5);
		gbc_foto.gridx = 0;
		gbc_foto.gridy = 1;
		panelFoto1.add(foto, gbc_foto);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
		button.setBackground(Color.WHITE);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		panelFoto1.add(button, gbc_button);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Comentario.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panelFoto1.add(btnNewButton_1, gbc_btnNewButton_1);

		JLabel lblMeGusta = new JLabel("26 Me gusta");
		GridBagConstraints gbc_lblMeGusta = new GridBagConstraints();
		gbc_lblMeGusta.insets = new Insets(0, 0, 5, 0);
		gbc_lblMeGusta.gridx = 3;
		gbc_lblMeGusta.gridy = 1;
		panelFoto1.add(lblMeGusta, gbc_lblMeGusta);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/perfil.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		panelFoto1.add(label, gbc_label);

		JLabel lblRaulgarcia = new JLabel("raul.garcia");
		GridBagConstraints gbc_lblRaulgarcia = new GridBagConstraints();
		gbc_lblRaulgarcia.insets = new Insets(0, 0, 5, 5);
		gbc_lblRaulgarcia.gridx = 2;
		gbc_lblRaulgarcia.gridy = 2;
		panelFoto1.add(lblRaulgarcia, gbc_lblRaulgarcia);

		JPanel panelFoto2 = new JPanel();
		panelCentro.add(panelFoto2);
		GridBagLayout gbl_panelFoto2 = new GridBagLayout();
		gbl_panelFoto2.columnWidths = new int[] { 192, 97, 97, 0, 0 };
		gbl_panelFoto2.rowHeights = new int[] { 20, 16, 0, 0, 0 };
		gbl_panelFoto2.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelFoto2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelFoto2.setLayout(gbl_panelFoto2);

		JLabel lblFoto2 = new JLabel("");
		lblFoto2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/paisajeTDS.png")));
		GridBagConstraints gbc_lblFoto2 = new GridBagConstraints();
		gbc_lblFoto2.gridheight = 2;
		gbc_lblFoto2.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoto2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblFoto2.gridx = 0;
		gbc_lblFoto2.gridy = 1;
		panelFoto2.add(lblFoto2, gbc_lblFoto2);

		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
		button_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		panelFoto2.add(button_1, gbc_button_1);

		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Comentario.png")));
		button_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 1;
		panelFoto2.add(button_2, gbc_button_2);

		JLabel lblMeGusta_1 = new JLabel("14 Me gusta");
		GridBagConstraints gbc_lblMeGusta_1 = new GridBagConstraints();
		gbc_lblMeGusta_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblMeGusta_1.gridx = 3;
		gbc_lblMeGusta_1.gridy = 1;
		panelFoto2.add(lblMeGusta_1, gbc_lblMeGusta_1);

		JPanel panelFoto3 = new JPanel();
		panelCentro.add(panelFoto3);

		JPanel panelFoto4 = new JPanel();
		panelCentro.add(panelFoto4);*/
		framePrincipal.getContentPane().add(scrollFotos, BorderLayout.CENTER);

	}

}
