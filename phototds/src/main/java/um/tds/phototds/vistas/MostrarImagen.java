package um.tds.phototds.vistas;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MostrarImagen {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MostrarImagen(String path) {
		initialize(path);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String path) {
		frame = new JFrame();
		Image imgPanel = new ImageIcon(path).getImage();
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JLabel lblimagen = new JLabel("");
		if(imgPanel.getWidth(null) > 1600 || imgPanel.getWidth(null) > 900) {
			frame.setSize(1600, 900);
			lblimagen.setIcon(new ImageIcon(imgPanel.getScaledInstance(1600, 900, Image.SCALE_SMOOTH)));
		}
		else {
			frame.setSize(imgPanel.getWidth(null), imgPanel.getHeight(null));
			lblimagen.setIcon(new ImageIcon(imgPanel));
		}

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.add(lblimagen);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowDeactivated(WindowEvent e) {
				frame.dispose();
			}
		});
	}

}
