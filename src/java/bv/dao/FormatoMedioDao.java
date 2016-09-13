package bv.dao;

import java.util.List;
import bv.entidad.FormatoMedio;

/**
 *
 * @author virtual
 */
public interface FormatoMedioDao {

    List<Object[]> llenaComboFormatoMedio();

    int crearEntidad(FormatoMedio fm);

    int actualizarEntidad(FormatoMedio fm);

    FormatoMedio buscarEntidad(int codigo);

}
