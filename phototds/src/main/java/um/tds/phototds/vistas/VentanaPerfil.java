package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.dominio.Usuario;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTabbedPane;

public class VentanaPerfil {

	private JFrame framePerfil;
	private JTextField txtBuscador;
	private Controlador controlador;
	private Usuario usuarioActual;
	private static final Color DEFAULT_BACKGROUND = new Color(102, 10, 45);

	/**
	 * Create the application.
	 */
	public VentanaPerfil() {
		controlador = Controlador.getUnicaInstancia();
		initialize();
	}

	public void mostrarVentana() {
		framePerfil.setLocationRelativeTo(null);
		framePerfil.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		usuarioActual = controlador.getUsuarioActual();
		framePerfil = new JFrame();
		framePerfil.setBounds(100, 100, 668, 458);
		framePerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelNorte = new JPanel();
		framePerfil.getContentPane().add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 0, 0, 50, 0, 0, 0, 0, 0, 0, 50, 0, 0 };
		gbl_panelNorte.rowHeights = new int[] { 10, 0, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		JButton btnAddFoto = new JButton("+");
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditorPane editorPane = new JEditorPane();
				framePerfil.getContentPane().add(editorPane);
				editorPane.setContentType("text/html");
				editorPane.setText(
						"<h1>Agregar Foto</h1><p>Anímate a compartir una foto con tus amigos.<br> Puedes arrastrar el fichero aquí. </p>");
				editorPane.setEditable(false);
				editorPane.setDropTarget(new DropTarget() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					public synchronized void drop(DropTargetDropEvent evt) {
						try {
							evt.acceptDrop(DnDConstants.ACTION_COPY);
							@SuppressWarnings("unchecked")
							List<File> droppedFiles = (List<File>) evt.getTransferable()
									.getTransferData(DataFlavor.javaFileListFlavor);
							for (File file : droppedFiles) {
								System.out.println(file.getPath());
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				});
			}
		});

		JButton button = new JButton("<-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.mostrarVentana();
				framePerfil.dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;
		panelNorte.add(button, gbc_button);

		JLabel lblPhototds = new JLabel("PhotoTDS");
		lblPhototds.setForeground(DEFAULT_BACKGROUND);
		lblPhototds.setFont(new Font("Verdana", Font.BOLD, 18));
		GridBagConstraints gbc_lblPhototds = new GridBagConstraints();
		gbc_lblPhototds.insets = new Insets(0, 0, 0, 5);
		gbc_lblPhototds.gridx = 2;
		gbc_lblPhototds.gridy = 1;
		panelNorte.add(lblPhototds, gbc_lblPhototds);
		btnAddFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddFoto.setBackground(DEFAULT_BACKGROUND);
		btnAddFoto.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAddFoto = new GridBagConstraints();
		gbc_btnAddFoto.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddFoto.gridx = 3;
		gbc_btnAddFoto.gridy = 1;
		panelNorte.add(btnAddFoto, gbc_btnAddFoto);

		JButton btnA = new JButton("A+");
		btnA.setForeground(Color.WHITE);
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnA.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_btnA = new GridBagConstraints();
		gbc_btnA.insets = new Insets(0, 0, 0, 5);
		gbc_btnA.gridx = 4;
		gbc_btnA.gridy = 1;
		panelNorte.add(btnA, gbc_btnA);

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
		btnBuscar.setIcon(new ImageIcon(VentanaPerfil.class.getResource("/um/tds/phototds/imagenes/lupa.png")));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscar.gridx = 8;
		gbc_btnBuscar.gridy = 1;
		panelNorte.add(btnBuscar, gbc_btnBuscar);
		
				JButton btnMenu = new JButton("");
				btnMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						JFrame frame = new JFrame();
						frame.setBounds(100, 100, 314, 164);

						JPanel panel = new JPanel();
						frame.getContentPane().add(panel, BorderLayout.CENTER);
						panel.setLayout(new GridLayout(0, 1, 0, 0));

						JButton btnGenerarPDF = new JButton("GenerarPDF");
						btnGenerarPDF.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
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
									JOptionPane.showMessageDialog(frame, "Ahora eres usuario PREMIUM", "¡Nuevas Funciones!",
											JOptionPane.INFORMATION_MESSAGE);
									btnGenerarexcel.setEnabled(true);
									btnGenerarPDF.setEnabled(true);
									btnFotosConMas.setEnabled(true);
									frame.dispose();
								}
								else JOptionPane.showMessageDialog(framePerfil, "Ya eres usuario premium!",
										"Premium", JOptionPane.INFORMATION_MESSAGE);
							}
						});
						panel.add(btnPremium);
						JButton btnCerrarSesin = new JButton("Cerrar Sesión");
						btnCerrarSesin.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								Login ventanaLogin = new Login();
								ventanaLogin.mostrarVentana();
								frame.dispose();
								framePerfil.dispose();
							}
						});
						panel.add(btnCerrarSesin);
						frame.setLocationRelativeTo(btnMenu);
						frame.setVisible(true);

					}
				});
				btnMenu.setIcon(new ImageIcon(VentanaPerfil.class.getResource("/um/tds/phototds/imagenes/list.png")));
				btnMenu.setBackground(DEFAULT_BACKGROUND);
				GridBagConstraints gbc_btnMenu = new GridBagConstraints();
				gbc_btnMenu.insets = new Insets(0, 0, 0, 5);
				gbc_btnMenu.gridx = 9;
				gbc_btnMenu.gridy = 1;
				panelNorte.add(btnMenu, gbc_btnMenu);

		JPanel panelCentro = new JPanel();
		framePerfil.getContentPane().add(panelCentro, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentro = new GridBagLayout();
		gbl_panelCentro.columnWidths = new int[] { 20, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentro.rowHeights = new int[] { 40, 0, 0, 0, 0 };
		gbl_panelCentro.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelCentro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentro.setLayout(gbl_panelCentro);
		
		Image img = new ImageIcon(controlador.getUsuarioActual().getImagenPath()).getImage();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridheight = 3;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panelCentro.add(label, gbc_label);

		JLabel lblNewLabel = new JLabel(controlador.getUsuarioActual().getNombre());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 1;
		panelCentro.add(lblNewLabel, gbc_lblNewLabel);

		JButton btnNewButton = new JButton("Editar Perfil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro ventanaRegistro = new Registro(true);
				ventanaRegistro.mostrarVentana();
				framePerfil.dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 1;
		panelCentro.add(btnNewButton, gbc_btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("13 publciaciones");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 3;
		panelCentro.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("7 seguidores");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 7;
		gbc_lblNewLabel_2.gridy = 3;
		panelCentro.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("7 seguidos");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridx = 9;
		gbc_lblNewLabel_3.gridy = 3;
		panelCentro.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JPanel panelSur = new JPanel();
		framePerfil.getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelSur.add(tabbedPane, BorderLayout.CENTER);

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
		panelCentro2.setLayout(new GridLayout(0, 1, 0, 10));

		JPanel panel = new JPanel();
		panelCentro2.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));


		JPanel PanelAlbumes = new JPanel();
		tabbedPane.addTab("ÁLBUMES", null, PanelAlbumes, null);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBackground(Color.WHITE);
		PanelAlbumes.add(panel_1, BorderLayout.CENTER);
		JList<Photo> listafotos = new JList<Photo>();
		DefaultListModel<Photo> fotoslistModel = new DefaultListModel<Photo>();
		listafotos.setModel(fotoslistModel);
		usuarioActual.getFotos().forEach(f -> fotoslistModel.addElement(f));
		listafotos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listafotos.setVisibleRowCount(-1);
		//listafotos.ensureIndexIsVisible(getHeight());
		listafotos.setCellRenderer(createListRenderer());
		panel_1.setViewportView(listafotos);}

	private static ListCellRenderer<? super Photo> createListRenderer() {
		return new DefaultListCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel) {
					JLabel label = (JLabel) c;
					label.setIcon(new ImageIcon(VentanaPerfil.class.getResource("/um/tds/phototds/imagenes/lupa.png")));
					if (!isSelected)
						label.setBackground(index % 2 == 0 ? background : defaultBackground);
				}
				return c;
			}
		};

	}

}
