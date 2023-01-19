package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import um.tds.phototds.dominio.Photo;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
	private String fotosFile;

	// private String userName;
	private static final Color DEFAULT_BACKGROUND = new Color(102, 10, 45);
	private JPanel panelCentralVentPrin;
	private JPanel panelCentro;
	private JScrollPane scrollFotos;
	private JPanel panelNorte;
	private JPanel panelCentralPerfil;
	private JPanel panelSurPerfil;

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		controlador = Controlador.getUnicaInstancia();
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

		// Estático
		panelNorte = new JPanel();
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

					// usuarioActual.setImagenPath(fichero.getAbsolutePath());
				}
			}
		});

		// Estático
		Luz luz = new Luz();
		luz.addEncendidoListener(new IEncendidoListener() {
			public void enteradoCambioEncendido(EventObject arg0) {
				if (luz.isEncendido()) {
					System.out.println("Encendido");
					JFileChooser choser = new JFileChooser();
					choser.setFileFilter(new FileNameExtensionFilter("XML", "xml"));
					int eleccion = choser.showOpenDialog(framePrincipal);
					if (eleccion == JFileChooser.APPROVE_OPTION) {
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

		JButton buttonAlbum = new JButton("A+");
		buttonAlbum.setForeground(Color.WHITE);
		buttonAlbum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonAlbum.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_buttonAlbum = new GridBagConstraints();
		gbc_buttonAlbum.insets = new Insets(0, 0, 0, 5);
		gbc_buttonAlbum.gridx = 3;
		gbc_buttonAlbum.gridy = 1;
		panelNorte.add(buttonAlbum, gbc_buttonAlbum);
		buttonAlbum.setVisible(false);

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

		JButton buttonVolver = new JButton("<-");
		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonVolver.setVisible(false);
				framePrincipal.getContentPane().remove(panelCentralPerfil);
				framePrincipal.getContentPane().add(scrollFotos);
				panelSurPerfil.setVisible(false);
				lblPhototds.setVisible(true);
				buttonAlbum.setVisible(false);
			}
		});
		buttonVolver.setForeground(Color.WHITE);
		buttonVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonVolver.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		panelNorte.add(buttonVolver, gbc_button);
		buttonVolver.setVisible(false);

		JButton btnUsuario = new JButton("");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				framePrincipal.getContentPane().remove(scrollFotos);
				framePrincipal.getContentPane().add(panelCentralPerfil);
				panelSurPerfil.setVisible(true);
				lblPhototds.setVisible(false);
				buttonVolver.setVisible(true);
				buttonAlbum.setVisible(true);

				/*
				 * VentanaPerfil ventanaPerfil = new VentanaPerfil();
				 * ventanaPerfil.mostrarVentana(); framePrincipal.dispose();
				 */
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
						JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
								JOptionPane.INFORMATION_MESSAGE);
						framePrincipal.enable();
					}
				});
				panel.add(btnGenerarPDF);

				JButton btnFotosConMas = new JButton("Fotos con mas MG");
				btnFotosConMas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						opciones.dispose();
						JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
								JOptionPane.INFORMATION_MESSAGE);
						framePrincipal.enable();
					}
				});
				panel.add(btnFotosConMas);

				JButton btnGenerarexcel = new JButton("GenerarExcel");
				btnGenerarexcel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						opciones.dispose();
						JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
								JOptionPane.INFORMATION_MESSAGE);
						framePrincipal.enable();
					}
				});
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
							JOptionPane.showMessageDialog(framePrincipal, "Ahora eres usuario PREMIUM",
									"¡Nuevas Funciones!", JOptionPane.INFORMATION_MESSAGE);

						} else {
							opciones.dispose();
							JOptionPane.showMessageDialog(framePrincipal, "Ya eres usuario premium!", "Premium",
									JOptionPane.INFORMATION_MESSAGE);

						}
						framePrincipal.enable();
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

		panelCentralVentPrin = new JPanel();
		BorderLayout bl_panelCentral = new BorderLayout();
		bl_panelCentral.setVgap(10);
		panelCentralVentPrin.setLayout(bl_panelCentral);
		// framePrincipal.getContentPane().add(panelCentralVentPrin,
		// BorderLayout.CENTER);

		scrollFotos = new JScrollPane(panelCentralVentPrin);
		panelCentralVentPrin.setAutoscrolls(true);

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.LIGHT_GRAY);
		panelCentralVentPrin.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 10));

		if (!controlador.obtenerFotos().isEmpty()) {
			for (Photo foto : controlador.obtenerFotos()) {
				JPanel panelFoto = new JPanel();
				panelCentro.add(panelFoto);
				GridBagLayout gbl_panelFoto1 = new GridBagLayout();
				gbl_panelFoto1.columnWidths = new int[] { 192, 97, 97, 0, 0 };
				gbl_panelFoto1.rowHeights = new int[] { 20, 40, 0, 0, 0 };
				gbl_panelFoto1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				gbl_panelFoto1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
				panelFoto.setLayout(gbl_panelFoto1);

				Image imgPanel = new ImageIcon(foto.getPath()).getImage();
				JLabel lblimagen = new JLabel("");
				lblimagen.setIcon(new ImageIcon(imgPanel.getScaledInstance(240, 160, Image.SCALE_SMOOTH)));
				GridBagConstraints gbc_foto = new GridBagConstraints();
				gbc_foto.fill = GridBagConstraints.HORIZONTAL;
				gbc_foto.gridheight = 2;
				gbc_foto.anchor = GridBagConstraints.NORTH;
				gbc_foto.insets = new Insets(0, 0, 5, 5);
				gbc_foto.gridx = 0;
				gbc_foto.gridy = 1;
				panelFoto.add(lblimagen, gbc_foto);

				JButton buttonCorazon = new JButton("");
				buttonCorazon.setIcon(
						new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
				buttonCorazon.setBackground(Color.WHITE);
				GridBagConstraints gbc_buttonCorazon = new GridBagConstraints();
				gbc_buttonCorazon.insets = new Insets(0, 0, 5, 5);
				gbc_buttonCorazon.gridx = 1;
				gbc_buttonCorazon.gridy = 1;
				panelFoto.add(buttonCorazon, gbc_buttonCorazon);

				JButton buttonComentarios = new JButton("");
				buttonComentarios.setBackground(Color.WHITE);
				buttonComentarios.setIcon(
						new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Comentario.png")));
				GridBagConstraints gbc_buttonComentarios = new GridBagConstraints();
				gbc_buttonComentarios.insets = new Insets(0, 0, 5, 5);
				gbc_buttonComentarios.anchor = GridBagConstraints.WEST;
				gbc_buttonComentarios.gridx = 2;
				gbc_buttonComentarios.gridy = 1;
				panelFoto.add(buttonComentarios, gbc_buttonComentarios);

				JLabel lblMeGusta = new JLabel(Integer.toString(foto.getMeGusta()) + " Me Gustas");
				GridBagConstraints gbc_lblMeGusta = new GridBagConstraints();
				gbc_lblMeGusta.insets = new Insets(0, 0, 5, 0);
				gbc_lblMeGusta.gridx = 3;
				gbc_lblMeGusta.gridy = 1;
				panelFoto.add(lblMeGusta, gbc_lblMeGusta);

				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 0, 5, 5);
				gbc_label.gridx = 1;
				gbc_label.gridy = 2;
				panelFoto.add(label, gbc_label);

				JLabel lblRaulgarcia = new JLabel(Controlador.getUnicaInstancia().getUsuarioActual().getUsername());
				GridBagConstraints gbc_lblRaulgarcia = new GridBagConstraints();
				gbc_lblRaulgarcia.insets = new Insets(0, 0, 5, 5);
				gbc_lblRaulgarcia.gridx = 2;
				gbc_lblRaulgarcia.gridy = 2;
				panelFoto.add(lblRaulgarcia, gbc_lblRaulgarcia);
			}
		}

		framePrincipal.getContentPane().add(scrollFotos, BorderLayout.CENTER);

		// PANELES PERFIL
		panelCentralPerfil = new JPanel();
		// framePrincipal.getContentPane().add(panelCentralPerfil, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 20, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentro.rowHeights = new int[] { 40, 0, 0, 0, 0 };
		gbl_panelCentro.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelCentro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentralPerfil.setLayout(gbl_panelCentro);

		// Image img = new
		// ImageIcon(controlador.getUsuarioActual().getImagenPath()).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 3;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panelCentralPerfil.add(label, gbc_label);

		JLabel lblNewLabel = new JLabel(controlador.getUsuarioActual().getNombre());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		panelCentralPerfil.add(lblNewLabel, gbc_lblNewLabel);

		JButton btnNewButton = new JButton("Editar Perfil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro ventanaRegistro = new Registro(true);
				ventanaRegistro.mostrarVentana();
				framePrincipal.dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 1;
		panelCentralPerfil.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(Integer.toString(controlador.obtenerNumeroPubls()) + " publicaciones");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 3;
		panelCentralPerfil.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("0 seguidores");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 7;
		gbc_lblNewLabel_2.gridy = 3;
		panelCentralPerfil.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("0 seguidos");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridx = 9;
		gbc_lblNewLabel_3.gridy = 3;
		panelCentralPerfil.add(lblNewLabel_3, gbc_lblNewLabel_3);

		panelSurPerfil = new JPanel();
		panelSurPerfil.setPreferredSize(new Dimension(300, 200));
		framePrincipal.getContentPane().add(panelSurPerfil, BorderLayout.SOUTH);
		panelSurPerfil.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelSurPerfil.add(tabbedPane, BorderLayout.CENTER);
		panelSurPerfil.setVisible(false);

		JPanel PanelFotos = new JPanel();
		tabbedPane.addTab("FOTOS", null, PanelFotos, null);
		PanelFotos.setLayout(new BorderLayout(0, 0));

		JPanel panelScroll = new JPanel();
		panelScroll.setLayout(new BorderLayout());
		PanelFotos.add(panelScroll, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(panelScroll);
		PanelFotos.add(scrollPane);

		JPanel panelCentro2 = new JPanel();
		panelCentro2.setBackground(Color.LIGHT_GRAY);
		panelScroll.add(panelCentro2, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro2 = new GridBagLayout();
		gbl_panelCentro2.columnWidths = new int[] { 200, 200, 200, 0 };
		gbl_panelCentro2.rowHeights = new int[] { 168, 0, 0, 0 };
		gbl_panelCentro2.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelCentro2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentro2.setLayout(gbl_panelCentro2);

		if (!controlador.obtenerFotos().isEmpty()) {
			int i = 0; //Columna
			int j = 0; //fila
			for (Photo foto : controlador.obtenerFotos()) {
				if(i != 0 && i%3 == 0) {
					i=0;
					j+=1;
				}
				Image imgPanel = new ImageIcon(foto.getPath()).getImage();
				

				JLabel lblHola = new JLabel();
				lblHola.setIcon(new ImageIcon(imgPanel.getScaledInstance(210, 160, Image.SCALE_SMOOTH)));
				GridBagConstraints gbc_lblHola = new GridBagConstraints();
				gbc_lblHola.anchor = GridBagConstraints.WEST;
				gbc_lblHola.insets = new Insets(0, 0, 5, 5);
				gbc_lblHola.gridx = i;
				gbc_lblHola.gridy = j;
				panelCentro2.add(lblHola, gbc_lblHola);
				i++;
			}
		}

		JPanel PanelAlbumes = new JPanel();
		tabbedPane.addTab("ÁLBUMES", null, PanelAlbumes, null);

		/*
		 * JScrollPane panel_1 = new JScrollPane(); panel_1.setBackground(Color.WHITE);
		 * PanelAlbumes.add(panel_1, BorderLayout.CENTER); JList<Photo> matrizFotos =
		 * new JList<Photo>(); DefaultListModel<Photo> fotoslistModel = new
		 * DefaultListModel<Photo>(); matrizFotos.setModel(fotoslistModel);
		 * controlador.obtenerFotos().forEach(f -> fotoslistModel.addElement(f));
		 * matrizFotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		 * matrizFotos.setVisibleRowCount(-1);
		 * //listafotos.ensureIndexIsVisible(getHeight());
		 * matrizFotos.setCellRenderer(createListRenderer());
		 * panel_1.setViewportView(matrizFotos);}
		 * 
		 * private static ListCellRenderer<? super Photo> createListRenderer() { return
		 * new DefaultListCellRenderer() {
		 * 
		 * private static final long serialVersionUID = 1L; private Color background =
		 * new Color(0, 100, 255, 15); private Color defaultBackground = (Color)
		 * UIManager.get("List.background");
		 * 
		 * @Override public Component getListCellRendererComponent(JList<?> list, Object
		 * value, int index, boolean isSelected, boolean cellHasFocus) { Component c =
		 * super.getListCellRendererComponent(list, value, index, isSelected,
		 * cellHasFocus); if (c instanceof JLabel) { JLabel label = (JLabel) c;
		 * label.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(
		 * "/um/tds/phototds/imagenes/lupa.png"))); if (!isSelected)
		 * label.setBackground(index % 2 == 0 ? background : defaultBackground); }
		 * return c; } };
		 */

	}

}
