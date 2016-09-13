package bv.dao;

import java.util.List;
import bv.entidad.Editorial;

/**
 *
 * @author virtual
 */
public interface EditorialDao {

    int crearEntidad(Editorial ed);

    int actualizarEntidad(Editorial ed);

    List<Editorial> obtenerEntidades();

    Editorial buscarEntidad(int codigo);

    List<Object[]> llenaComboEditorial(String idBiblioteca);

}
