package um.tds.phototds.persistencia;


public abstract class FactoriaDAO {
	public static final String DAO_TDS = "um.tds.phototds.persistencia.TDSFactoriaDAO";

	private static FactoriaDAO unicaInstancia = null;

	/**
	 * Crea un tipo de factoria DAO. Solo existe el tipo TDSFactoriaDAO
	 */
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null)
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}

	public static FactoriaDAO getInstancia() throws DAOException {
		if (unicaInstancia == null)
			return getInstancia(FactoriaDAO.DAO_TDS);
		else
			return unicaInstancia;
	}

	protected FactoriaDAO() {
	}

	// Metodos factoria para obtener adaptadores

	public abstract UsuarioDAO getUsuarioDAO();
	public abstract PublicacionDAO getPublicacionDAO();
}
