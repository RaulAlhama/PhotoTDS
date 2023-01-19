package um.tds.phototds.dominio;

public class DescuentoPopularidad implements Descuento {
	private double descuento;
	private String message;
	
	public DescuentoPopularidad() {
		this.descuento = 0.1;
		this.message = "DESCUENTO POR SER POPULAR - 10%";
	}
	
	@Override
	public double aplicarDescuento(double precio) {
		// TODO Auto-generated method stub
		return precio * (1- descuento);
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuario.esPopular();
	}

	@Override
	public double getDescuento() {
		// TODO Auto-generated method stub
		return descuento;
	}

	@Override
	public String getMensaje() {
		// TODO Auto-generated method stub
		return message;
	}

}
