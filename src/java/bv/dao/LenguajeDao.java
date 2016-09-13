package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Lenguaje;

/**
 *
 * @author virtual
 */
public interface LenguajeDao {

    List<Object[]> llenaComboLenguaje();

    ArrayList<String> lisLenguajeDocumentalXidDocumental(String idDocumental);

    List<Lenguaje> listarSerieIdDocumental(String idDocumental);

}
