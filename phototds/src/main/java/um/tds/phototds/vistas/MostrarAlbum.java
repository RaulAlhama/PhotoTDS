package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Album;
import um.tds.phototds.dominio.Photo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class MostrarAlbum {

	private JFrame frameMostrarAlbum;
	private Controlador controlador;
	private JScrollPane scrollFotos;
	private JPanel panelCentral;
	private JPanel panelCentro;
	private JLabel lblMeGusta;
	private JButton buttonCorazon;
	private JButton buttonVolver;
	private boolean isSubiendoFoto;

	/**
	 * Create the application.
	 */
	public MostrarAlbum(Album album) {
		controlador = Controlador.getUnicaInstancia();
		initialize(album);
		isSubiendoFoto = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Album album) {
		frameMostrarAlbum = new JFrame();
		frameMostrarAlbum.setBounds(100, 100, 669, 461);
		frameMostrarAlbum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameMostrarAlbum.setLocationRelativeTo(null);
		frameMostrarAlbum.setUndecorated(true);
		frameMostrarAlbum.setVisible(true);

		frameMostrarAlbum.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				if (!isSubiendoFoto)
					frameMostrarAlbum.dispose();
			}
		});

		JPanel panelNorte = new JPanel();
		frameMostrarAlbum.getContentPane().add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[] { 0, 0, 116, 65, 55, 0, 30, 30, 10, 20, 10, 0, 10, 0 };
		gbl_panelNorte.rowHeights = new int[] { 29, 0 };
		gbl_panelNorte.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelNorte.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelNorte.setLayout(gbl_panelNorte);

		JButton botonAddFoto = new JButton("+");
		botonAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isSubiendoFoto = true;
				JFileChooser chooser = new JFileChooser();
				int result = chooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					isSubiendoFoto = true;
					File fichero = chooser.getSelectedFile();
					controlador.addFotoToAlbum(album, fichero.getAbsolutePath());
					isSubiendoFoto = false;
				}
			}
		});

		buttonVolver = new JButton("<-");
		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameMostrarAlbum.dispose();
			}
		});
		buttonVolver.setForeground(Color.WHITE);
		buttonVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonVolver.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_buttonVolver = new GridBagConstraints();
		gbc_buttonVolver.insets = new Insets(0, 0, 0, 5);
		gbc_buttonVolver.gridx = 1;
		gbc_buttonVolver.gridy = 0;
		panelNorte.add(buttonVolver, gbc_buttonVolver);

		botonAddFoto.setForeground(Color.WHITE);
		botonAddFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		botonAddFoto.setBackground(new Color(102, 10, 45));
		GridBagConstraints gbc_botonAddFoto = new GridBagConstraints();
		gbc_botonAddFoto.insets = new Insets(0, 0, 0, 5);
		gbc_botonAddFoto.gridx = 2;
		gbc_botonAddFoto.gridy = 0;
		panelNorte.add(botonAddFoto, gbc_botonAddFoto);

		JLabel lblTituloAlbum = new JLabel(album.getTitulo());
		lblTituloAlbum.setFont(new Font("Quicksand", Font.PLAIN, 20));
		GridBagConstraints gbc_lblTituloAlbum = new GridBagConstraints();
		gbc_lblTituloAlbum.gridwidth = 5;
		gbc_lblTituloAlbum.anchor = GridBagConstraints.WEST;
		gbc_lblTituloAlbum.insets = new Insets(0, 0, 0, 5);
		gbc_lblTituloAlbum.gridx = 3;
		gbc_lblTituloAlbum.gridy = 0;
		panelNorte.add(lblTituloAlbum, gbc_lblTituloAlbum);

		lblMeGusta = new JLabel(Integer.toString(album.getMeGusta()) + " Me Gustas");
		GridBagConstraints gbc_lblMeGusta = new GridBagConstraints();
		gbc_lblMeGusta.anchor = GridBagConstraints.EAST;
		gbc_lblMeGusta.insets = new Insets(0, 0, 0, 5);
		gbc_lblMeGusta.gridx = 11;
		gbc_lblMeGusta.gridy = 0;
		panelNorte.add(lblMeGusta, gbc_lblMeGusta);

		buttonCorazon = new JButton("");
		buttonCorazon.setIcon(new ImageIcon(MostrarAlbum.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
		GridBagConstraints gbc_buttonCorazon = new GridBagConstraints();
		gbc_buttonCorazon.anchor = GridBagConstraints.EAST;
		gbc_buttonCorazon.gridx = 12;
		gbc_buttonCorazon.gridy = 0;
		panelNorte.add(buttonCorazon, gbc_buttonCorazon);

		buttonCorazon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonCorazon.setBackground(Color.RED);
				lblMeGusta.setText(album.getMeGusta() + " Me Gustas");
				controlador.addMeGusta(album);
			}
		});

		panelCentral = new JPanel();
		BorderLayout bl_panelCentral = new BorderLayout();
		bl_panelCentral.setVgap(10);
		panelCentral.setLayout(bl_panelCentral);
		scrollFotos = new JScrollPane(panelCentral);
		panelCentral.setAutoscrolls(true);

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.LIGHT_GRAY);
		panelCentral.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 1, 0, 10));

		/*
		 * JPanel panelFoto = new JPanel(); // System.out.println(foto.getHashtags());
		 * panelCentro.add(panelFoto); GridBagLayout gbl_panelFoto1 = new
		 * GridBagLayout(); gbl_panelFoto1.columnWidths = new int[] { 192, 97, 97, 0, 0
		 * }; gbl_panelFoto1.rowHeights = new int[] { 20, 40, 0, 0, 0 };
		 * gbl_panelFoto1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
		 * Double.MIN_VALUE }; gbl_panelFoto1.rowWeights = new double[] { 0.0, 0.0, 0.0,
		 * 0.0, Double.MIN_VALUE }; panelFoto.setLayout(gbl_panelFoto1);
		 * 
		 * Image imgPanel = new ImageIcon(VentanaPrincipal.class.getResource(
		 * "/um/tds/phototds/imagenes/Corazon.png")).getImage(); JLabel lblimagen = new
		 * JLabel(""); lblimagen.setIcon(new ImageIcon(imgPanel.getScaledInstance(240,
		 * 160, Image.SCALE_SMOOTH))); GridBagConstraints gbc_foto = new
		 * GridBagConstraints(); gbc_foto.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_foto.gridheight = 2; gbc_foto.anchor = GridBagConstraints.NORTH;
		 * gbc_foto.insets = new Insets(0, 0, 5, 5); gbc_foto.gridx = 0; gbc_foto.gridy
		 * = 1; panelFoto.add(lblimagen, gbc_foto);
		 * 
		 * 
		 * JButton buttonMG = new JButton(""); buttonMG.setIcon( new
		 * ImageIcon(VentanaPrincipal.class.getResource(
		 * "/um/tds/phototds/imagenes/Corazon.png")));
		 * buttonMG.setBackground(Color.WHITE); GridBagConstraints gbc_buttonMG = new
		 * GridBagConstraints(); gbc_buttonMG.insets = new Insets(0, 0, 5, 5);
		 * gbc_buttonMG.gridx = 1; gbc_buttonMG.gridy = 1;
		 * 
		 * panelFoto.add(buttonMG, gbc_buttonMG);
		 * 
		 * JButton buttonComentarios = new JButton("");
		 * buttonComentarios.setBackground(Color.WHITE); buttonComentarios.setIcon( new
		 * ImageIcon(VentanaPrincipal.class.getResource(
		 * "/um/tds/phototds/imagenes/Comentario.png"))); GridBagConstraints
		 * gbc_buttonComentarios = new GridBagConstraints();
		 * gbc_buttonComentarios.insets = new Insets(0, 0, 5, 5);
		 * gbc_buttonComentarios.anchor = GridBagConstraints.WEST;
		 * gbc_buttonComentarios.gridx = 2; gbc_buttonComentarios.gridy = 1;
		 * 
		 * 
		 * 
		 * panelFoto.add(buttonComentarios, gbc_buttonComentarios);
		 * 
		 * JLabel lblMGusta = new JLabel(" Me Gustas"); GridBagConstraints gbc_lblMGusta
		 * = new GridBagConstraints(); gbc_lblMGusta.insets = new Insets(0, 0, 5, 0);
		 * gbc_lblMGusta.gridx = 3; gbc_lblMGusta.gridy = 1; panelFoto.add(lblMGusta,
		 * gbc_lblMGusta);
		 * 
		 * 
		 * JLabel lblComentarios = new JLabel(" Comentarios"); GridBagConstraints
		 * gbc_lblComentarios = new GridBagConstraints(); gbc_lblComentarios.insets =
		 * new Insets(0, 0, 5, 0); gbc_lblComentarios.gridx = 4;
		 * gbc_lblComentarios.gridy = 1; panelFoto.add(lblComentarios,
		 * gbc_lblComentarios);
		 */

		for (Photo foto : album.getFotos()) {
			JPanel panelFoto = new JPanel();
			// System.out.println(foto.getHashtags());
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
			lblimagen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new MostrarImagen(foto.getPath());
				}
			});

			JButton buttonMG = new JButton("");
			buttonMG.setIcon(
					new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Corazon.png")));
			buttonMG.setBackground(Color.WHITE);
			GridBagConstraints gbc_buttonMG = new GridBagConstraints();
			gbc_buttonMG.insets = new Insets(0, 0, 5, 5);
			gbc_buttonMG.gridx = 1;
			gbc_buttonMG.gridy = 1;

			panelFoto.add(buttonMG, gbc_buttonMG);

			JButton buttonComentarios = new JButton("");
			buttonComentarios.setBackground(Color.WHITE);
			buttonComentarios.setIcon(
					new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/Comentario.png")));
			GridBagConstraints gbc_buttonComentarios = new GridBagConstraints();
			gbc_buttonComentarios.insets = new Insets(0, 0, 5, 5);
			gbc_buttonComentarios.anchor = GridBagConstraints.WEST;
			gbc_buttonComentarios.gridx = 2;
			gbc_buttonComentarios.gridy = 1;

			buttonComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// JOptionPane.showMessageDialog(frameMostrarAlbum, "FUNCION SIN IMPLEMENTAR",
					// "¡AVISO!",JOptionPane.INFORMATION_MESSAGE);
				}
			});

			panelFoto.add(buttonComentarios, gbc_buttonComentarios);

			JLabel lblMGusta = new JLabel(Integer.toString(foto.getMeGusta()) + " Me Gustas");
			GridBagConstraints gbc_lblMGusta = new GridBagConstraints();
			gbc_lblMGusta.insets = new Insets(0, 0, 5, 0);
			gbc_lblMGusta.gridx = 3;
			gbc_lblMGusta.gridy = 1;
			panelFoto.add(lblMGusta, gbc_lblMGusta);

			buttonMG.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controlador.addMeGusta(foto);
					lblMGusta.setText(foto.getMeGusta() + " Me Gustas");
				}
			});

			JLabel lblComentarios = new JLabel(Integer.toString(foto.getComentarios().size()) + " Comentarios");
			GridBagConstraints gbc_lblComentarios = new GridBagConstraints();
			gbc_lblComentarios.insets = new Insets(0, 0, 5, 0);
			gbc_lblComentarios.gridx = 4;
			gbc_lblComentarios.gridy = 1;
			panelFoto.add(lblComentarios, gbc_lblComentarios);

		}

		frameMostrarAlbum.getContentPane().add(scrollFotos, BorderLayout.CENTER);
	}

}
