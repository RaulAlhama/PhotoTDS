package um.tds.phototds.dominio;

public interface Descuento {
	public double aplicarDescuento(double precio);

	public boolean isApplicable(Usuario usuario);

	public double getDescuento();

	public String getMensaje();
}
