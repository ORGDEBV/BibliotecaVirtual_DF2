package bv.dao;

import java.util.ArrayList;
import bv.entidad.AuxContenido;

/**
 *
 * @author virtual
 */
public interface TablaContenidoDao {
    
    ArrayList<AuxContenido> lstTablaContenidosXidDocumental(String idDocumental);
    
}
