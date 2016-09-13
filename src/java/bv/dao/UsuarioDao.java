package bv.dao;

import java.util.List;
import bv.entidad.Usuario;

/**
 *
 * @author virtual
 */
public interface UsuarioDao {

    int crearEntidad(Usuario u);

    int actualizarEntidad(Usuario u, int idUsuario);

    int resetContrasena(Usuario u, int idUsuario);

    List<Usuario> obtenerEntidades();

    List<Usuario> obtenerEntidadesParametros(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca);

    List<Object[]> obtenerTipousuario(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca);

    int cambiarContrasena(Usuario usuario, int idUsuario);

}
