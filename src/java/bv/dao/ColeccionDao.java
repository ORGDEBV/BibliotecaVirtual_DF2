package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Coleccion;

/**
 *
 * @author virtual
 */
public interface ColeccionDao {

    int crearEntidad(Coleccion coleccion);

    int actualizarEntidad(Coleccion coleccion);

    List<Object[]> llenaComboColeccion(String idBiblioteca);

    ArrayList<String> listColeccionDocumentallXidDocumental(String idDocumental);

    List<Coleccion> obtenerSeleccion(String colecciones);

    List<Coleccion> listarColeccionIdDocumental(String idDocumental);

    int crearEntidad(Coleccion coleccion, String idBiblioiteca);

}
