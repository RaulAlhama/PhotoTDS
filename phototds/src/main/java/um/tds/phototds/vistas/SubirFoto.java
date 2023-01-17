package um.tds.phototds.vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class SubirFoto {

	private JFrame frameAddFoto;
	private Controlador controlador;

	/**
	 * Create the application.
	 */
	public SubirFoto(String path) {
		controlador = Controlador.getUnicaInstancia();
		initialize(path);
	}
	
	public void mostrarVentana() {
		frameAddFoto.setLocationRelativeTo(null);
		frameAddFoto.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String path) {
		frameAddFoto = new JFrame();
		frameAddFoto.setBounds(100, 100, 746, 444);
		frameAddFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = (FlowLayout) panelBotones.getLayout();
		fl_panelBotones.setAlignment(FlowLayout.RIGHT);
		frameAddFoto.getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		
		
		JPanel panelDerecha = new JPanel();
		frameAddFoto.getContentPane().add(panelDerecha, BorderLayout.EAST);
		GridBagLayout gbl_panelDerecha = new GridBagLayout();
		gbl_panelDerecha.columnWidths = new int[]{285, 0, 0};
		gbl_panelDerecha.rowHeights = new int[]{26, 323, 0};
		gbl_panelDerecha.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDerecha.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
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
		panel_4.add(textArea);
		
		JPanel panelImagen = new JPanel();
		frameAddFoto.getContentPane().add(panelImagen, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		panelImagen.add(label);
		
		String imagenPath = path;
		Image img = new ImageIcon(imagenPath).getImage();
		// JLabel lblFoto = new JLabel("");
		label.setIcon(new ImageIcon(img.getScaledInstance(450,338, Image.SCALE_SMOOTH)));
		
		JButton btnCompartir = new JButton("Compartir");
		btnCompartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = textArea.getText();
				controlador.compartirFoto(texto, path);
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
				ventanaPrincipal.mostrarVentana();
				frameAddFoto.dispose();
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
	}

}
