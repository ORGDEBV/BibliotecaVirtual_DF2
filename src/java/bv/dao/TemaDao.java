package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Tema;

/**
 *
 * @author virtual
 */
public interface TemaDao {

    int crearEntidad(Tema tema);

    int actualizarEntidad(Tema tema);

    List<Tema> obtenerEntidades();

    List<Object[]> llenaComboTema(String idBiblioteca);

    ArrayList<String> listTemaDocumentalXidDocumental(String idDocumental);

    List<Tema> listarSerieIdDocumental(String idDocumental);

    ArrayList<Tema> obtenerSelecionTemas(String s);

}
