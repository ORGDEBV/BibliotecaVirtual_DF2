package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Serie;

/**
 *
 * @author virtual
 */
public interface SerieDao {

    int crearEntidad(Serie serie);

    int actualizarEntidad(Serie serie);

    ArrayList<String> listSerieDocumentallXidDocumental(String idDocumental);

    List<Serie> obtenerSeleccion(String series);

    List<Object[]> llenaComboSerie(String idBiblioteca);

    List<Serie> listarSerieIdDocumental(String idDocumental);

    int crearEntidad(Serie serie, String idBiblioteca);

}
