package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Autor;

/**
 *
 * @author virtual
 */
public interface AutorDao {

    List<Autor> obtenerEntidades(String idBiblioteca);

    int crearEntidad(Autor aut, int idUsuario);

    int eliminarEntidad(int codigo, int idUsuario);

    int actualizarEntidad(Autor aut, int idUsuario);

    int buscarEntidad(Autor aut);

    int buscarEntidadAlternativo(Autor aut);

    int crearEntidadAlternativo(Autor aut, int idUsuario);

    List<Object[]> cboAutores(String idBiblioteca);

    ArrayList<String> listAutorDocumentallXidDocumental(String idDocumental);

    List<Autor> listarAutorIdDocumental(String idDocumental);

}
