package bv.dao;

import java.util.List;
import bv.entidad.Novedad;

/**
 *
 * @author virtual
 */
public interface NovedadDao {

    int obetenerId();

    List<Novedad> listarNovedades();

}
