package um.tds.phototds;

import java.awt.EventQueue;

public class App {
	//Lanzamos la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Login2 ventanaLogin = new Login2();
					ventanaLogin.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
