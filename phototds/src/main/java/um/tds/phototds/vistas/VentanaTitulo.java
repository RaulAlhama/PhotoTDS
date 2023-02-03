package um.tds.phototds.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import um.tds.phototds.controlador.Controlador;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaTitulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Controlador controlador;

	public VentanaTitulo(JFrame framePrincipal) {
		controlador = Controlador.getUnicaInstancia();
		setBounds(100, 100, 409, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Introduce un título");
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(20);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (controlador.comprobarTitulo(textField.getText())) {
							dispose();
							JFileChooser chooser = new JFileChooser();
							int result = chooser.showSaveDialog(null);
							if (result == JFileChooser.APPROVE_OPTION) {
								File fichero = chooser.getSelectedFile();
								SubirPublicacion frameSubir = new SubirPublicacion(fichero.getAbsolutePath(), true,textField.getText());
								frameSubir.mostrarVentana();
								framePrincipal.dispose();
							}
						} else {
							JOptionPane.showMessageDialog(getContentPane(), "Ya existe un albúm con ese nombre",
									"Error", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
