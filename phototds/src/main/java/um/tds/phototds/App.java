package um.tds.phototds;

import java.awt.EventQueue;

import um.tds.phototds.vistas.Login;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Login ventanaLogin = new Login();
					ventanaLogin.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
