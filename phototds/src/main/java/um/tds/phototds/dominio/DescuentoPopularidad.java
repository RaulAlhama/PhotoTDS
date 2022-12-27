package um.tds.phototds.dominio;

public class DescuentoPopularidad implements Descuento {

	@Override
	public double aplicarDescuento(double precio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isApplicable(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
