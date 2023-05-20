package um.tds.phototds;

import java.awt.EventQueue;

import um.tds.phototds.vistas.VentanaLogin;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					VentanaLogin ventanaLogin = new VentanaLogin();
					ventanaLogin.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
