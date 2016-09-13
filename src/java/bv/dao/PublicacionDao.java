package bv.dao;

import java.util.ArrayList;
import bv.dto.PublicacionDto;

/**
 *
 * @author virtual
 */
public interface PublicacionDao {

    ArrayList<PublicacionDto> listPublicacion(String ID_PERFIL, String ID_BIBLIOTECA, String visibilidad);

    int cambiaVisibilidad(PublicacionDto pub);

}
