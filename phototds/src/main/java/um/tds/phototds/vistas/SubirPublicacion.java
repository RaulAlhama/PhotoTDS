package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import um.tds.phototds.controlador.Controlador;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SubirPublicacion {

	private JFrame frameAddFoto;
	private Controlador controlador;

	/**
	 * Create the application.
	 */
	// Si true -> VentanaPrincipal
	// Si false -> VentanaPerfil
	public SubirPublicacion(String path, boolean isAlbum,String titulo) {
		controlador = Controlador.getUnicaInstancia();
		initialize(path, isAlbum,titulo);
	}

	public void mostrarVentana() {
		frameAddFoto.setLocationRelativeTo(null);
		frameAddFoto.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String path, boolean isAlbum, String titulo) {
		frameAddFoto = new JFrame();
		frameAddFoto.setBounds(100, 100, 746, 444);
		frameAddFoto.setUndecorated(true);
		frameAddFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.RIGHT);
		frameAddFoto.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		JPanel panelDerecha = new JPanel();
		frameAddFoto.getContentPane().add(panelDerecha, BorderLayout.EAST);
		GridBagLayout gbl_panelDerecha = new GridBagLayout();
		gbl_panelDerecha.columnWidths = new int[] { 285, 0, 0 };
		gbl_panelDerecha.rowHeights = new int[] { 26, 323, 0 };
		gbl_panelDerecha.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelDerecha.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelDerecha.setLayout(gbl_panelDerecha);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panelDerecha.add(panel_3, gbc_panel_3);

		JLabel lblEscribeUnComentario = new JLabel("Escribe un comentario (máximo 200 caracteres)");
		panel_3.add(lblEscribeUnComentario);
		lblEscribeUnComentario.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 0, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panelDerecha.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		panel_4.add(textArea);

		JPanel panelImagen = new JPanel();
		frameAddFoto.getContentPane().add(panelImagen, BorderLayout.CENTER);

		JLabel label = new JLabel("");
		panelImagen.add(label);

		String imagenPath = path;
		Image img = new ImageIcon(imagenPath).getImage();
		// JLabel lblFoto = new JLabel("");
		label.setIcon(new ImageIcon(img.getScaledInstance(450, 338, Image.SCALE_SMOOTH)));

		JButton btnCompartir = new JButton("Compartir");
		btnCompartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textArea.getText().length() > 200) {
					JOptionPane.showMessageDialog(textArea, "La descripción debe contener como máximo 200 caracteres",
							"Error al compartir", JOptionPane.WARNING_MESSAGE);
				} else {
					String texto = textArea.getText();
					List<String> hashtags = obtenerHashtags(texto);
					if (verificarHashtags(hashtags)) {
						if (isAlbum)
							controlador.compartirAlbum(titulo,texto,path,hashtags);
						else
							controlador.compartirFoto(texto, path, hashtags);
						VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
						ventanaPrincipal.mostrarVentana();
						frameAddFoto.dispose();
					} else {
						JOptionPane.showMessageDialog(textArea, "Los hashtags no pueden tener más de 15 letras",
								"Error al compartir", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(btnCompartir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.mostrarVentana();
				frameAddFoto.dispose();
			}
		});
		panelBotones.add(btnCancelar);
		
		if(isAlbum) {
			JPanel panel = new JPanel();
			frameAddFoto.getContentPane().add(panel, BorderLayout.NORTH);
			
			JLabel lblNewLabel = new JLabel(titulo);
			panel.add(lblNewLabel);
		}
	
	}

	public List<String> obtenerHashtags(String texto) {
		List<String> hashtags = new ArrayList<>();
		String[] words = texto.split("\\s+");
		for (String word : words) {
			if (word.startsWith("#")) {
				hashtags.add(word);
			}
		}
		return hashtags;
	}

	public boolean verificarHashtags(List<String> hashtags) {
		for (String hashtag : hashtags) {
			if (hashtag.length() > 15)
				return false;
		}
		return true;
	}

}
