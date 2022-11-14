package um.tds.phototds.vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
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
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame framePrincipal;
	private JTextField txtBuscador;
	private static final Color DEFAULT_BACKGROUND = new Color(102, 10, 45);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
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

		JPanel panel = new JPanel();
		framePrincipal.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 50, 0, 0, 0, 0, 0, 0, 30, 50, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 10, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblPhototds = new JLabel("PhotoTDS");
		lblPhototds.setForeground(DEFAULT_BACKGROUND);
		lblPhototds.setFont(new Font("Verdana", Font.BOLD, 18));
		GridBagConstraints gbc_lblPhototds = new GridBagConstraints();
		gbc_lblPhototds.insets = new Insets(0, 0, 0, 5);
		gbc_lblPhototds.gridx = 1;
		gbc_lblPhototds.gridy = 1;
		panel.add(lblPhototds, gbc_lblPhototds);

		JButton btnAddFoto = new JButton("+");
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JEditorPane editorPane = new JEditorPane();
				framePrincipal.getContentPane().add(editorPane);
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
		btnAddFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddFoto.setBackground(DEFAULT_BACKGROUND);
		btnAddFoto.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnAddFoto = new GridBagConstraints();
		gbc_btnAddFoto.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddFoto.gridx = 4;
		gbc_btnAddFoto.gridy = 1;
		panel.add(btnAddFoto, gbc_btnAddFoto);

		txtBuscador = new JTextField();
		txtBuscador.setText("Buscador");
		GridBagConstraints gbc_txtBuscador = new GridBagConstraints();
		gbc_txtBuscador.gridwidth = 3;
		gbc_txtBuscador.insets = new Insets(0, 0, 0, 5);
		gbc_txtBuscador.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBuscador.gridx = 5;
		gbc_txtBuscador.gridy = 1;
		panel.add(txtBuscador, gbc_txtBuscador);
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
		panel.add(btnBuscar, gbc_btnBuscar);

		JButton btnUsuario = new JButton("");
		btnUsuario.setBackground(DEFAULT_BACKGROUND);
		btnUsuario.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/perfil.png")));
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_btnUsuario.gridx = 11;
		gbc_btnUsuario.gridy = 1;
		panel.add(btnUsuario, gbc_btnUsuario);

		JButton btnMenu = new JButton("");
		btnMenu.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/um/tds/phototds/imagenes/list.png")));
		btnMenu.setBackground(DEFAULT_BACKGROUND);
		GridBagConstraints gbc_btnMenu = new GridBagConstraints();
		gbc_btnMenu.insets = new Insets(0, 0, 0, 5);
		gbc_btnMenu.gridx = 12;
		gbc_btnMenu.gridy = 1;
		panel.add(btnMenu, gbc_btnMenu);
	}

}
