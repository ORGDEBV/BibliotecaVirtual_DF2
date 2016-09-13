package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Contribuidor;

/**
 *
 * @author virtual
 */
public interface ContribuidorDao {

    ArrayList<Contribuidor> listarContribuidorXidDocumental(String idDocumental);

    List<Contribuidor> listarContribuidorIdDocumental(String idDocumental);

}
