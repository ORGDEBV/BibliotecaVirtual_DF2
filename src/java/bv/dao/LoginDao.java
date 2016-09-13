package bv.dao;

import bv.entidad.Usuario;

/**
 *
 * @author virtual
 */
public interface LoginDao {

    Usuario validar(String user, String contrasena);

    int recuperar(String email);

}
