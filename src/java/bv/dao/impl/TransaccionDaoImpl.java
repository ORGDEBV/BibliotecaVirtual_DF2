package bv.dao.impl;

import bv.dao.TransaccionDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.AuxContenido;
import bv.entidad.Contribuidor;
import bv.entidad.Documental;
import bv.entidad.ImagenNovedad;
import bv.entidad.LogTabla;
import bv.entidad.Novedad;
import bv.util.ConectaDb;
import bv.util.dateConverter;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class TransaccionDaoImpl implements TransaccionDao{

    dateConverter converter;

    public TransaccionDaoImpl() {
        this.converter = new dateConverter();
    }

    @Override
    public String insertTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException {
        Connection conn = ConectaDb.getConnection();
        String insert = "Faltan completar campos.";
        try {
            conn.setAutoCommit(false);
            CallableStatement callDocumental = conn.prepareCall("{call BV.SP_MANTENIMIENTO_DOCUMENTAL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            callDocumental.setString(1, documental.getID_DOCUMENTAL());
            callDocumental.setString(2, documental.getTITULO());
            callDocumental.setString(3, documental.getTITULO_ALTERNATIVO());
            callDocumental.setString(4, documental.getDESCRIPCION());
            callDocumental.setString(5, documental.getRESUMEN());
            if (documental.getID_TIPO() == null || documental.getID_TIPO().equals(-1)) {
                callDocumental.setString(6, null);
            } else {
                callDocumental.setInt(6, documental.getID_TIPO());
            }
            callDocumental.setInt(7, documental.getID_BIBLIOTECA_FUENTE());
            callDocumental.setString(8, documental.getTIENE_COMO_VERSION());
            callDocumental.setString(9, documental.getES_PARTE_DE());
            callDocumental.setString(10, documental.getTIENE_PARTE_DE());
            if (documental.getID_COBERTURA_ESPACIAL().equals(-1)) {
                callDocumental.setString(11, null);
            } else {
                callDocumental.setInt(11, documental.getID_COBERTURA_ESPACIAL());
            }
            if (documental.getID_COBERTURA_TEMPORAL() == null || documental.getID_COBERTURA_TEMPORAL().equals(-1)) {
                callDocumental.setString(12, null);
            } else {
                callDocumental.setInt(12, documental.getID_COBERTURA_TEMPORAL());
            }
            callDocumental.setString(13, "");//FALTA
            callDocumental.setString(14, documental.getFECHA_PUBLICACION());
            if (documental.getFECHA_ACEPTACION() == null) {
                callDocumental.setString(15, null);
            } else {
                callDocumental.setDate(15, converter.formatFecha(documental.getFECHA_ACEPTACION()));
            }
            if (documental.getFECHA_COPYRIGHT() == null) {
                callDocumental.setString(16, null);
            } else {
                callDocumental.setDate(16, converter.formatFecha(documental.getFECHA_COPYRIGHT()));
            }
            if (documental.getID_FORMATO() == null || documental.getID_FORMATO().equals("-1")) {
                documental.setID_FORMATO(null);
            }
            callDocumental.setString(17, documental.getID_FORMATO());
            callDocumental.setString(18, documental.getFORMATO_EXTENSION());
            if (documental.getID_FORMATO_MEDIO() == null || documental.getID_FORMATO_MEDIO().equals(-1)) {
                callDocumental.setString(19, null);
            } else {
                callDocumental.setInt(19, documental.getID_FORMATO_MEDIO());
            }
            if (documental.getID_EDITORIAL() == null || documental.getID_EDITORIAL().equals(-1)) {
                callDocumental.setString(20, null);
            } else {
                callDocumental.setInt(20, documental.getID_EDITORIAL());
            }
            callDocumental.setString(21, documental.getDERECHO());
            callDocumental.setString(22, documental.getDERECHO_ACCESO());
            if (documental.getID_AUDIENCIA() == null || documental.getID_AUDIENCIA().equals(-1)) {
                callDocumental.setString(23, null);
            } else {
                callDocumental.setInt(23, documental.getID_AUDIENCIA());
            }
            callDocumental.setString(24, documental.getURL());
            callDocumental.setString(25, documental.getISBN());
            callDocumental.setString(26, documental.getOTRO());
            callDocumental.setString(27, documental.getNUMERO_PAGINAS());
            callDocumental.setInt(28, 0);
            callDocumental.setInt(29, documental.getID_TIPO_OTRO());
            callDocumental.setString(30, documental.getPAIS_DEFINIDO());
            callDocumental.setString(31, documental.getCIUDAD_DEFINIDA());
            if (documental.getPAGINA_INICIO() == null) {
                callDocumental.setString(32, null);
            } else {
                callDocumental.setInt(32, documental.getPAGINA_INICIO());
            }
            if (documental.getPAGINA_FIN() == null) {
                callDocumental.setString(33, null);
            } else {
                callDocumental.setInt(33, documental.getPAGINA_FIN());
            }
            callDocumental.setString(34, documental.getNOTA());
            callDocumental.setString(35, "DOCUMENTAL_INS");
            callDocumental.executeQuery();

            CallableStatement callContribuidor = conn.prepareCall("{call BV.SP_MANTENIMIENTO_CONTRIBUIDOR(?,?,?,?)}");
            for (Contribuidor contribuidor : listContribuidor) {
                callContribuidor.setInt(1, 0);
                callContribuidor.setString(2, documental.getID_DOCUMENTAL());
                callContribuidor.setString(3, contribuidor.getCONTRIBUIDOR());
                callContribuidor.setString(4, "INSERT_CONTRIBUIDOR");
                callContribuidor.executeQuery();
            }
            CallableStatement callTema = conn.prepareCall("{call BV.SP_MANTENIMIENTO_TEMA_DOCUMENTAL(?,?,?)}");
//            for (int i = 0; i < listTema.size(); i++) {
//                callTema.setInt(1, Integer.parseInt(listTema.get(i)));
//                callTema.setString(2, documental.getID_DOCUMENTAL());
//                callTema.setString(3, "TEMA_DOCUMENTAL_INS");
//                callTema.executeQuery();
//            }
            for (String listTema1 : listTema) {
                callTema.setInt(1, Integer.parseInt(listTema1));
                callTema.setString(2, documental.getID_DOCUMENTAL());
                callTema.setString(3, "TEMA_DOCUMENTAL_INS");
                callTema.executeQuery();
            }
            //------------ relacion documental
            CallableStatement callRelacionDocumental = conn.prepareCall("{call BV.SP_MANTENIMIENTO_DOCUMENTAL_RELACION_DOCUMENTAL(?,?,?)}");
            for (String RelacionDocumental1 : lisRelacionDocumental) {
                callRelacionDocumental.setString(1, documental.getID_DOCUMENTAL());
                callRelacionDocumental.setString(2, RelacionDocumental1);
                callRelacionDocumental.setString(3, "INSERT");
                callRelacionDocumental.executeQuery();
            }
            CallableStatement callLenguaje = conn.prepareCall("{call BV.SP_MANTENIMIENTO_LENGUAJE_DOCUMENTAL(?,?,?)}");//            
            for (String lisLenguaje1 : listLenguaje) {
                callLenguaje.setString(1, lisLenguaje1);
                callLenguaje.setString(2, documental.getID_DOCUMENTAL());
                callLenguaje.setString(3, "LENGUAJE_DOCUMENTAL_INS");
                callLenguaje.executeQuery();
            }
            CallableStatement callTablaContenido = conn.prepareCall("{call BV.SP_MANTENIMIENTO_TABLA_CONTENIDO (?,?,?,?,?)}");
            for (AuxContenido tablaContenido : listTablaContenido) {
                callTablaContenido.setInt(1, 0);
                callTablaContenido.setString(2, tablaContenido.getIndice());
                callTablaContenido.setString(3, tablaContenido.getTema());
                callTablaContenido.setString(4, documental.getID_DOCUMENTAL());
                callTablaContenido.setString(5, "TABLA_CONTENIDO_INS");
                callTablaContenido.executeQuery();
            }
            CallableStatement callAutor = conn.prepareCall("{call BV.SP_MANTENIMIENTO_AUTOR_DOCUMENTAL (?,?,?)}");
            for (String listAutor1 : listAutor) {
                callAutor.setInt(1, Integer.parseInt(listAutor1));
                callAutor.setString(2, documental.getID_DOCUMENTAL());
                callAutor.setString(3, "AUTOR_DOCUMENTAL_INS");
                callAutor.executeQuery();
            }
            CallableStatement callColeccion = conn.prepareCall("{call BV.SP_MANTENIMIENTO_COLECCION_DOCUMENTAL (?,?,?)}");
            for (String listColeccion1 : listColeccion) {
                callColeccion.setInt(1, Integer.parseInt(listColeccion1));
                callColeccion.setString(2, documental.getID_DOCUMENTAL());
                callColeccion.setString(3, "COLECCION_DOCUMENTAL_INS");
                callColeccion.executeQuery();
            }
            CallableStatement callSerie = conn.prepareCall("{call BV.SP_MANTENIMIENTO_SERIE_DOCUMENTAL (?,?,?)}");
            for (String listSerie1 : listSerie) {
                callSerie.setInt(1, Integer.parseInt(listSerie1));
                callSerie.setString(2, documental.getID_DOCUMENTAL());
                callSerie.setString(3, "SERIE_DOCUMENTAL_INS");
                callSerie.executeQuery();
            }
            CallableStatement callHistorial = conn.prepareCall("{call BV.SP_MANTENIMIENTO_HISTORIAL_ACCION_DOCUMENTAL (?,?,?,?,?,?)}");
            callHistorial.setInt(1, 0);
            callHistorial.setString(2, documental.getID_DOCUMENTAL());
            callHistorial.setInt(3, 1);
            callHistorial.setString(4, "");
            callHistorial.setInt(5, ID_USUARIO);
            callHistorial.setString(6, "HISTORIA_ACCION_INS");
            callHistorial.executeQuery();
            conn.commit();
            insert = "1";
        } catch (SQLException ex) {
            insert = ex.getMessage();
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        } finally {
            conn.setAutoCommit(true);
            return insert;
        }
    }

    @Override
    public String modificarTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException {
        Connection conn = ConectaDb.getConnection();
        String upd = "Faltan completar campos.";
        try {
            conn.setAutoCommit(false);

            CallableStatement callDocumental = conn.prepareCall("{call BV.SP_MANTENIMIENTO_DOCUMENTAL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            callDocumental.setString(1, documental.getID_DOCUMENTAL());
            callDocumental.setString(2, documental.getTITULO());
            callDocumental.setString(3, documental.getTITULO_ALTERNATIVO());
            callDocumental.setString(4, documental.getDESCRIPCION());
            callDocumental.setString(5, documental.getRESUMEN());
            if (documental.getID_TIPO().equals(-1)) {
                callDocumental.setString(6, null);
            } else {
                callDocumental.setInt(6, documental.getID_TIPO());
            }
            callDocumental.setInt(7, documental.getID_BIBLIOTECA_FUENTE());

//            if (documental.getID_DOCUMENTAL_RELACION().equals("-1")) {
//                documental.setID_DOCUMENTAL_RELACION(null);
//            }
            //callDocumental.setString(8, documental.getID_DOCUMENTAL_RELACION());
            callDocumental.setString(8, documental.getTIENE_COMO_VERSION());
            callDocumental.setString(9, documental.getES_PARTE_DE());
            callDocumental.setString(10, documental.getTIENE_PARTE_DE());
            if (documental.getID_COBERTURA_ESPACIAL().equals(-1)) {
                callDocumental.setString(11, null);
            } else {
                callDocumental.setInt(11, documental.getID_COBERTURA_ESPACIAL());
            }
            if (documental.getID_COBERTURA_TEMPORAL().equals(-1)) {
                callDocumental.setString(12, null);
            } else {
                callDocumental.setInt(12, documental.getID_COBERTURA_TEMPORAL());
            }
            callDocumental.setString(13, "");//FALTA
            callDocumental.setString(14, documental.getFECHA_PUBLICACION());
            if (documental.getFECHA_ACEPTACION() == null) {
                callDocumental.setString(15, null);
            } else {
                callDocumental.setDate(15, converter.formatFecha(documental.getFECHA_ACEPTACION()));
            }
            if (documental.getFECHA_COPYRIGHT() == null) {
                callDocumental.setString(16, null);
            } else {
                callDocumental.setDate(16, converter.formatFecha(documental.getFECHA_COPYRIGHT()));
            }
            if (documental.getID_FORMATO().equals("-1")) {
                documental.setID_FORMATO(null);
            }
            callDocumental.setString(17, documental.getID_FORMATO());
            callDocumental.setString(18, documental.getFORMATO_EXTENSION());
            if (documental.getID_FORMATO_MEDIO().equals(-1)) {
                callDocumental.setString(19, null);
            } else {
                callDocumental.setInt(19, documental.getID_FORMATO_MEDIO());
            }
            if (documental.getID_EDITORIAL().equals(-1)) {
                callDocumental.setString(20, null);
            } else {
                callDocumental.setInt(20, documental.getID_EDITORIAL());
            }
            callDocumental.setString(21, documental.getDERECHO());
            callDocumental.setString(22, documental.getDERECHO_ACCESO());
            if (documental.getID_AUDIENCIA().equals(-1)) {
                callDocumental.setString(23, null);
            } else {
                callDocumental.setInt(23, documental.getID_AUDIENCIA());
            }
            callDocumental.setString(24, documental.getURL());
            callDocumental.setString(25, documental.getISBN());
            callDocumental.setString(26, documental.getOTRO());
            callDocumental.setString(27, documental.getNUMERO_PAGINAS());
            callDocumental.setInt(28, 0);
            callDocumental.setInt(29, documental.getID_TIPO_OTRO());

            callDocumental.setString(30, documental.getPAIS_DEFINIDO());
            callDocumental.setString(31, documental.getCIUDAD_DEFINIDA());
            callDocumental.setInt(32, documental.getPAGINA_INICIO());
            callDocumental.setInt(33, documental.getPAGINA_FIN());
            callDocumental.setString(34, documental.getNOTA());
            callDocumental.setString(35, "DOCUMENTAL_UPD");
            callDocumental.executeQuery();

            CallableStatement callContribuidor = conn.prepareCall("{call BV.SP_MANTENIMIENTO_CONTRIBUIDOR(?,?,?,?)}");
            for (Contribuidor contribuidor : listContribuidor) {
                callContribuidor.setInt(1, 0);
                callContribuidor.setString(2, documental.getID_DOCUMENTAL());
                callContribuidor.setString(3, contribuidor.getCONTRIBUIDOR());
                callContribuidor.setString(4, "INSERT_CONTRIBUIDOR");
                callContribuidor.executeQuery();
            }
            CallableStatement callTema = conn.prepareCall("{call BV.SP_MANTENIMIENTO_TEMA_DOCUMENTAL(?,?,?)}");
            for (int i = 0; i < listTema.size(); i++) {
                callTema.setInt(1, Integer.parseInt(listTema.get(i)));
                callTema.setString(2, documental.getID_DOCUMENTAL());
                callTema.setString(3, "TEMA_DOCUMENTAL_INS");
                callTema.executeQuery();
            }
            //------------ relacion documental
            CallableStatement callRelacionDocumental = conn.prepareCall("{call BV.SP_MANTENIMIENTO_DOCUMENTAL_RELACION_DOCUMENTAL(?,?,?)}");
            for (int i = 0; i < lisRelacionDocumental.size(); i++) {
                callRelacionDocumental.setString(1, documental.getID_DOCUMENTAL());
                callRelacionDocumental.setString(2, lisRelacionDocumental.get(i));
                callRelacionDocumental.setString(3, "INSERT");
                callRelacionDocumental.executeQuery();
            }
            CallableStatement callLenguaje = conn.prepareCall("{call BV.SP_MANTENIMIENTO_LENGUAJE_DOCUMENTAL(?,?,?)}");
            for (int i = 0; i < listLenguaje.size(); i++) {
                callLenguaje.setString(1, listLenguaje.get(i));
                callLenguaje.setString(2, documental.getID_DOCUMENTAL());
                callLenguaje.setString(3, "LENGUAJE_DOCUMENTAL_INS");
                callLenguaje.executeQuery();
            }
            CallableStatement callTablaContenido = conn.prepareCall("{call BV.SP_MANTENIMIENTO_TABLA_CONTENIDO (?,?,?,?,?)}");
            for (AuxContenido tablaContenido : listTablaContenido) {
                callTablaContenido.setInt(1, 0);
                callTablaContenido.setString(2, tablaContenido.getIndice());
                callTablaContenido.setString(3, tablaContenido.getTema());
                callTablaContenido.setString(4, documental.getID_DOCUMENTAL());
                callTablaContenido.setString(5, "TABLA_CONTENIDO_INS");
                callTablaContenido.executeQuery();
            }
            CallableStatement callAutor = conn.prepareCall("{call BV.SP_MANTENIMIENTO_AUTOR_DOCUMENTAL (?,?,?)}");
            for (int i = 0; i < listAutor.size(); i++) {
                callAutor.setInt(1, Integer.parseInt(listAutor.get(i)));
                callAutor.setString(2, documental.getID_DOCUMENTAL());
                callAutor.setString(3, "AUTOR_DOCUMENTAL_INS");
                callAutor.executeQuery();
            }
            CallableStatement callColeccion = conn.prepareCall("{call BV.SP_MANTENIMIENTO_COLECCION_DOCUMENTAL (?,?,?)}");
            for (int i = 0; i < listColeccion.size(); i++) {
                callColeccion.setInt(1, Integer.parseInt(listColeccion.get(i)));
                callColeccion.setString(2, documental.getID_DOCUMENTAL());
                callColeccion.setString(3, "COLECCION_DOCUMENTAL_INS");
                callColeccion.executeQuery();
            }
            CallableStatement callSerie = conn.prepareCall("{call BV.SP_MANTENIMIENTO_SERIE_DOCUMENTAL (?,?,?)}");
            for (int i = 0; i < listSerie.size(); i++) {
                callSerie.setInt(1, Integer.parseInt(listSerie.get(i)));
                callSerie.setString(2, documental.getID_DOCUMENTAL());
                callSerie.setString(3, "SERIE_DOCUMENTAL_INS");
                callSerie.executeQuery();
            }
            CallableStatement callHistorial = conn.prepareCall("{call BV.SP_MANTENIMIENTO_HISTORIAL_ACCION_DOCUMENTAL (?,?,?,?,?,?)}");
            callHistorial.setInt(1, 0);
            callHistorial.setString(2, documental.getID_DOCUMENTAL());
            callHistorial.setInt(3, 2);
            callHistorial.setString(4, "");
            callHistorial.setInt(5, ID_USUARIO);
            callHistorial.setString(6, "HISTORIA_ACCION_INS");
            callHistorial.executeQuery();

            conn.commit();
            upd = "1";
        } catch (SQLException ex) {
            upd = ex.getMessage();
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        } finally {
            conn.setAutoCommit(true);
            return upd;
        }
    }

    @Override
    public String deleteTransaccion(String ID_DOCUMENTAL, int ID_USUARIO) throws SQLException {
        Connection conn = ConectaDb.getConnection();
        String insert = "Ocurrió un error.";
        try {
            conn.setAutoCommit(false);

            CallableStatement callDocumental = conn.prepareCall("{call BV.SP_DESACTIVAR_DOCUMENTAL (?)}");
            callDocumental.setString(1, ID_DOCUMENTAL);
            callDocumental.executeQuery();

            CallableStatement callHistorial = conn.prepareCall("{call BV.SP_MANTENIMIENTO_HISTORIAL_ACCION_DOCUMENTAL (?,?,?,?,?,?)}");
            callHistorial.setInt(1, 0);
            callHistorial.setString(2, ID_DOCUMENTAL);
            callHistorial.setInt(3, 3);
            callHistorial.setString(4, "");
            callHistorial.setInt(5, ID_USUARIO);
            callHistorial.setString(6, "HISTORIA_ACCION_INS");
            callHistorial.executeQuery();
            conn.commit();
            insert = "1";
        } catch (SQLException ex) {
            insert = ex.getMessage();
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        } finally {
            conn.setAutoCommit(true);
            return insert;
        }
    }

    @Override
    public String insertTransaccionNovedades(Novedad novedad, List<ImagenNovedad> lstImagenNovedad, int imagenid, int idUsuario) throws SQLException {
        Connection conn = ConectaDb.getConnection();
        String insert = "Faltan completar campos.";
        try {
            conn.setAutoCommit(false);
            int id = 0;
            CallableStatement callNovedad = conn.prepareCall("{call web.SP_NOVEDADES_MANTENIMIENTO(?,?,?,?,?,?,?,?,?,?)}");
            callNovedad.setInt(1, 1);
            callNovedad.setString(2, novedad.getTITULO_CORTO());
            callNovedad.setString(3, novedad.getTITULO_COMPLETO());
            callNovedad.setString(4, novedad.getDESCRIPCION());
            callNovedad.setString(5, novedad.getCONTENIDO());
            if (novedad.getFECHA_NOVEDAD() == null) {
                callNovedad.setString(6, null);
            } else {
                callNovedad.setDate(6, converter.formatFecha(novedad.getFECHA_NOVEDAD()));
            }
            if (novedad.getFECHA_REGISTRO() == null) {
                callNovedad.setString(7, null);
            } else {
                callNovedad.setDate(7, converter.formatFecha(novedad.getFECHA_REGISTRO()));
            }
            callNovedad.setString(8, novedad.getMOSTAR_INICIO());
            callNovedad.setString(9, novedad.getACTIVO());
            callNovedad.setString(10, "INSERTAR");
            callNovedad.executeQuery();
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("web", "NOVEDADES", "", "1", String.valueOf(idUsuario)));

            //innecesario
            CallableStatement callImagenNovedad = conn.prepareCall("{call web.SP_IMAGEN_NOVEDAD_MANTENIMIENTO(?,?,?,?,?)}");
            for (ImagenNovedad imagenNovedad : lstImagenNovedad) {
                callImagenNovedad.setInt(1, 0);
                callImagenNovedad.setInt(2, imagenid);
                callImagenNovedad.setString(3, imagenNovedad.getURL_NOVEDAD());
                callImagenNovedad.setInt(4, imagenNovedad.getORDEN());
                callImagenNovedad.setString(5, "INSERTAR");
                callImagenNovedad.executeQuery();

            }
            conn.commit();
            insert = "1";

        } catch (SQLException ex) {
            insert = ex.getMessage();
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        } finally {
            conn.setAutoCommit(true);
            return insert;
        }
    }
}
