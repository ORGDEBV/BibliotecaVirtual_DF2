package bv.dao;

import java.util.List;
import bv.dto.PerfilDto;
import bv.entidad.PerfilDocumentalDetalle;

/**
 *
 * @author virtual
 */
public interface PerfilDocumentalDetalleDao {

    List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalle(String perfil);

    List<Object[]> obtenerPerfiles();

    int editarListPerfildocumentaldetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle, int idUsuario);

    PerfilDto obtenerPerfilXidDocumental(String idDocumental);

}
