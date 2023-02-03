package um.tds.phototds.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import um.tds.phototds.controlador.Controlador;

@SuppressWarnings("serial")
public class VentanaOpciones extends JFrame{

	private JFrame opciones;
	private Controlador controlador;

	public VentanaOpciones(JFrame framePrincipal,JButton menu) {
		controlador = Controlador.getUnicaInstancia();
		initialize(framePrincipal,menu);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame framePrincipal,JButton menu) {
		opciones = new JFrame();
		opciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		opciones.setTitle("Opciones");
		opciones.setBounds(100, 100, 314, 164);
		opciones.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				opciones.dispose();
			}
		});
		JPanel panel = new JPanel();
		opciones.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnGenerarPDF = new JButton("GenerarPDF");
		btnGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(btnGenerarPDF);

		JButton btnFotosConMas = new JButton("Fotos con mas MG");
		btnFotosConMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel.add(btnFotosConMas);

		JButton btnGenerarexcel = new JButton("GenerarExcel");
		btnGenerarexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opciones.dispose();
				JOptionPane.showMessageDialog(framePrincipal, "FUNCION SIN IMPLEMENTAR", "¡AVISO!",
						JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(framePrincipal, "Ahora eres usuario PREMIUM", "¡Nuevas Funciones!",
							JOptionPane.INFORMATION_MESSAGE);
					if (controlador.getDescuento().isPresent()) {
						String mensaje = controlador.getDescuento().get().getMensaje();
						JOptionPane.showMessageDialog(framePrincipal, "Descuento aplicado: " + mensaje, "Premium",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(framePrincipal, "No se ha aplicado ningún Descuento.", "Premium",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					opciones.dispose();
					JOptionPane.showMessageDialog(framePrincipal, "Ya eres usuario premium!", "Premium",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

		panel.add(btnPremium);
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaLogin ventanaLogin = new VentanaLogin();
				ventanaLogin.mostrarVentana();
				opciones.dispose();
				framePrincipal.dispose();
			}
		});
		panel.add(btnCerrarSesin);
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.RED);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opciones.dispose();
			}
		});
		panel.add(btnCancelar);
		opciones.setLocationRelativeTo(menu);
		opciones.setResizable(false);
		opciones.setVisible(true);

	}

}
