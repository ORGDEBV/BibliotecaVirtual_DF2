package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.Biblioteca;

/**
 *
 * @author virtual
 */
public interface BibliotecaDao {

    int crearEntidad(Biblioteca biblioteca, int idUsuario);

    int eliminarEntidad(int codigo, int idUsuario);

    int actualizarEntidad(Biblioteca biblioteca, int idUsuario);

    List<Biblioteca> obtenerEntidades();

    List<Object[]> obtenerRegiones();

    List<Object[]> obtenerProvincias(String idRegion);

    List<Object[]> obtenerDistritos(String idRegion, String idProvincia);

    Biblioteca cargarEntidad(String idBiblioteca);

    ArrayList<Biblioteca> lstMapeoBibliotecas(String idBiblioteca);

    Biblioteca oobtenerServidorBiblioteca(String idBiblioteca);

}
