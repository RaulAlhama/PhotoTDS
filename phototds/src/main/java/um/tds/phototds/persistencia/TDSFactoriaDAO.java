package um.tds.phototds.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {

	public TDSFactoriaDAO() {
	}

	@Override
	public TDSUsuarioDAO getUsuarioDAO() {
		return new TDSUsuarioDAO();
	}

	@Override
	public TDSPublicacionDAO getPublicacionDAO() {
		// TODO Auto-generated method stub
		return new TDSPublicacionDAO();
	}

}
