package um.tds.phototds.dominio;

public class DescuentoEdad implements Descuento {
	private double descuento;
	private String message;

	public DescuentoEdad() {
		this.descuento = 0.2;
		this.message = "DESCUENTO JOVEN - 20%";
	}

	@Override
	public double aplicarDescuento(double precio) {

		return precio * (1 - descuento);
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		return usuario.isJoven();
	}

	@Override
	public double getDescuento() {
		return descuento;
	}

	@Override
	public String getMensaje() {
		return message;
	}

}
