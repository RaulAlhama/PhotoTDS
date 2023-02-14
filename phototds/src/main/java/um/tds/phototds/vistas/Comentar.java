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

import javax.swing.border.BevelBorder;

import um.tds.phototds.controlador.Controlador;
import um.tds.phototds.dominio.Publicacion;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Comentar {

	private JFrame frameComentario;
	private Controlador controlador;

	/**
	 * Create the application.
	 */
	// Si true -> VentanaPrincipal
	// Si false -> VentanaPerfil
	public Comentar(Publicacion pub, String path) {
		controlador = Controlador.getUnicaInstancia();
		initialize(pub, path);
	}
	
	public void mostrarVentana() {
		frameComentario.setLocationRelativeTo(null);
		frameComentario.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Publicacion foto, String path) {
		frameComentario = new JFrame();
		frameComentario.setBounds(100, 100, 746, 444);
		frameComentario.setUndecorated(true);
		frameComentario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.RIGHT);
		frameComentario.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		
		
		JPanel panelDerecha = new JPanel();
		frameComentario.getContentPane().add(panelDerecha, BorderLayout.EAST);
		GridBagLayout gbl_panelDerecha = new GridBagLayout();
		gbl_panelDerecha.columnWidths = new int[]{285, 0, 0};
		gbl_panelDerecha.rowHeights = new int[]{26, 323, 0};
		gbl_panelDerecha.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDerecha.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelDerecha.setLayout(gbl_panelDerecha);
		
		JPanel panelTitulo = new JPanel();
		GridBagConstraints gbc_panelTitulo = new GridBagConstraints();
		gbc_panelTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_panelTitulo.fill = GridBagConstraints.BOTH;
		gbc_panelTitulo.gridx = 0;
		gbc_panelTitulo.gridy = 0;
		panelDerecha.add(panelTitulo, gbc_panelTitulo);
		
		JLabel lblInfo = new JLabel("Escribe un comentario (máximo 120 caracteres)");
		panelTitulo.add(lblInfo);
		
		JPanel panelTextArea = new JPanel();
		panelTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panelTextArea = new GridBagConstraints();
		gbc_panelTextArea.insets = new Insets(0, 0, 0, 5);
		gbc_panelTextArea.fill = GridBagConstraints.BOTH;
		gbc_panelTextArea.gridx = 0;
		gbc_panelTextArea.gridy = 1;
		panelDerecha.add(panelTextArea, gbc_panelTextArea);
		panelTextArea.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		panelTextArea.add(textArea);
		
		JPanel panelImagen = new JPanel();
		frameComentario.getContentPane().add(panelImagen, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		panelImagen.add(label);
		
		Image img = new ImageIcon(path).getImage();
		// JLabel lblFoto = new JLabel("");
		label.setIcon(new ImageIcon(img.getScaledInstance(450,338, Image.SCALE_SMOOTH)));
		
		JButton btnCompartir = new JButton("OK");
		btnCompartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText().length() > 120) {
					JOptionPane.showMessageDialog(textArea, "El comentario debe contener como máximo 120 caracteres",
							"Error en el registro", JOptionPane.WARNING_MESSAGE);
				}
				else {
				String texto = textArea.getText();
				controlador.addComentario(foto, texto);
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.mostrarVentana();
				frameComentario.dispose();
				}
			}
		});
		panelBotones.add(btnCompartir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.mostrarVentana();
				frameComentario.dispose();
			}
		});
		panelBotones.add(btnCancelar);
	}

}
