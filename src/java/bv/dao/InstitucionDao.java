package bv.dao;

import java.util.List;
import bv.entidad.Institucion;

/**
 *
 * @author virtual
 */
public interface InstitucionDao {

    int crearEntidad(Institucion institucion, int idUsuario);

    List<Institucion> obtenerEntidades();

    List<Object[]> llenaComboAvanzado();

    List<Object[]> llenaComboTipoInstitucion();

}
