package bv.dao;

import java.util.List;
import bv.entidad.LogTabla;

/**
 *
 * @author virtual
 */
public interface LogTablaDao {

    boolean registrarLogTabla(LogTabla lt);

    List<Object[]> listarLog();

    List<Object[]> listarLogDocumental();

}
