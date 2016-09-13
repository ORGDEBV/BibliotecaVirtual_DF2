package bv.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.AuxContenido;
import bv.entidad.Contribuidor;
import bv.entidad.Documental;
import bv.entidad.ImagenNovedad;
import bv.entidad.Novedad;

/**
 *
 * @author virtual
 */
public interface TransaccionDao {

    String insertTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException;

    String modificarTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException;

    String deleteTransaccion(String ID_DOCUMENTAL, int ID_USUARIO) throws SQLException;

    String insertTransaccionNovedades(Novedad novedad, List<ImagenNovedad> lstImagenNovedad, int imagenid, int idUsuario) throws SQLException;

}
