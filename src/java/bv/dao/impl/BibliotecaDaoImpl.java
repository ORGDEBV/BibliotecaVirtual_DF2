package bv.dao.impl;

import bv.dao.BibliotecaDao;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.Biblioteca;
import bv.entidad.LogTabla;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class BibliotecaDaoImpl implements BibliotecaDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Biblioteca biblioteca, int idUsuario) {
        int n = 0;
        String[] parametros = new String[16];
        parametros[0] = "";
        parametros[1] = biblioteca.getID_INSTITUCION_MEDIADOR() + "";
        parametros[2] = biblioteca.getNOMBRE_BIBLIOTECA();
        parametros[3] = biblioteca.getDIRECCION();
        parametros[4] = biblioteca.getEMAIL();
        parametros[5] = biblioteca.getCODIGO_SNB();
        parametros[6] = biblioteca.getID_DISTRITO() + "";
        parametros[7] = biblioteca.getID_PROVINCIA() + "";
        parametros[8] = biblioteca.getID_REGION() + "";
        parametros[9] = "";
        parametros[10] = "INSERT_BIBLIOTECA";
        parametros[11] = biblioteca.getURL();
        parametros[12] = biblioteca.getDIRECTORIO();
        
        
        if(biblioteca.getTITULO_MAPA()==null){
        biblioteca.setTITULO_MAPA("");
        }
        parametros[13] = biblioteca.getTITULO_MAPA();
        
         if(biblioteca.getLATITUD()==null){
        biblioteca.setLATITUD("");
        }
        parametros[14] = biblioteca.getLATITUD();
        
         if(biblioteca.getLONGITUD()==null){
        biblioteca.setLONGITUD("");
        }
        parametros[15] = biblioteca.getLONGITUD();
        
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "BIBLIOTECA", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo, int idUsuario) {
        int n = 0;
        String[] parametros = new String[16];
        parametros[0] = codigo + "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "BIBLIOTECA_DEL";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "BIBLIOTECA", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Biblioteca biblioteca, int idUsuario) {
        int n = 0;
        String[] parametros = new String[16];
        parametros[0] = String.valueOf(biblioteca.getID_BIBLIOTECA_MEDIADOR());
        parametros[1] = String.valueOf(biblioteca.getID_INSTITUCION_MEDIADOR());
        parametros[2] = biblioteca.getNOMBRE_BIBLIOTECA();
        parametros[3] = biblioteca.getDIRECCION();
        parametros[4] = biblioteca.getEMAIL();
        parametros[5] = biblioteca.getCODIGO_SNB();
        parametros[6] = biblioteca.getID_DISTRITO();
        parametros[7] = biblioteca.getID_PROVINCIA();
        parametros[8] = biblioteca.getID_REGION();
        if (biblioteca.getACTIVO() == true) {
            parametros[9] = "1";
        } else if (biblioteca.getACTIVO() == false) {
            parametros[9] = "0";
        }
        parametros[10] = "BIBLIOTECA_UPD";
        parametros[11] = biblioteca.getURL();
        parametros[12] = biblioteca.getDIRECTORIO();
        parametros[13] = biblioteca.getTITULO_MAPA();
        parametros[14] = biblioteca.getLATITUD();
        parametros[15] = biblioteca.getLONGITUD();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "BIBLIOTECA", "", "2", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public List<Biblioteca> obtenerEntidades() {
        Boolean activo = null;
        List<Object[]> objBiblioteca;
        List<Biblioteca> listBiblioteca = new ArrayList<>();
        String[] parametros = new String[16];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "LISTAR_BIBLIOTECA";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        objBiblioteca = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        for (Object[] obj : objBiblioteca) {
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(obj[0].toString()));
            biblioteca.setNOMBRE_BIBLIOTECA(obj[1].toString());
            biblioteca.setDIRECCION(obj[2].toString());
            biblioteca.setEMAIL(obj[3].toString());
            biblioteca.setID_INSTITUCION_MEDIADOR(Integer.parseInt(obj[4].toString()));
            biblioteca.setNOMBRE_INSTITUCION(obj[5].toString());
            biblioteca.setCODIGO_SNB(obj[6].toString());
            biblioteca.setID_DISTRITO(obj[7].toString());
            biblioteca.setDISTRITO(obj[8].toString());
            biblioteca.setID_PROVINCIA(obj[9].toString());
            biblioteca.setPROVINCIA(obj[10].toString());
            biblioteca.setID_REGION(obj[11].toString());
            biblioteca.setREGION(obj[12].toString());
            if (obj[13].toString().equals("1")) {
                activo = true;
            } else if (obj[13].toString().equals("0")) {
                activo = false;
            }
            biblioteca.setACTIVO(activo);
            biblioteca.setNRO_PERSONAL(Integer.parseInt(obj[14].toString()));
            biblioteca.setURL(obj[15].toString());
            biblioteca.setDIRECTORIO(obj[16].toString());
            biblioteca.setTITULO_MAPA(obj[17].toString());
            biblioteca.setLATITUD(obj[18].toString());
            biblioteca.setLONGITUD(obj[19].toString());

            listBiblioteca.add(biblioteca);
        }
        return listBiblioteca;
    }
    
    @Override
    public List<Object[]> obtenerRegiones() {
        String[] parametros = new String[16];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "LISTAR_REGION";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        List<Object[]> regiones = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        return regiones;
    }

    @Override
    public List<Object[]> obtenerProvincias(String idRegion) {
        String[] parametros = new String[16];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = idRegion;
        parametros[9] = "";
        parametros[10] = "LISTAR_PROVINCIA";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        List<Object[]> provincias = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        return provincias;
    }

    @Override
    public List<Object[]> obtenerDistritos(String idRegion, String idProvincia) {
        String[] parametros = new String[16];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = idProvincia;
        parametros[8] = idRegion;
        parametros[9] = "";
        parametros[10] = "LISTAR_DISTRITO";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        List<Object[]> distritos = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        return distritos;
    }

    @Override
    public Biblioteca cargarEntidad(String idBiblioteca) {
        Biblioteca biblioteca = null;
        List<Object[]> objBiblioteca;
        String[] parametros = new String[16];
        parametros[0] = idBiblioteca;
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "LISTAR_BIBLIOTECA_XID";
        parametros[11] = "";
        parametros[12] = "";
        parametros[13] = "";
        parametros[14] = "";
        parametros[15] = "";
        objBiblioteca = conector.execProcedure("BV.SP_MANTENIMIENTO_BIBLIOTECA", parametros);
        for (Object[] obj : objBiblioteca) {
            biblioteca = new Biblioteca();
            biblioteca.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(obj[0].toString()));
            biblioteca.setNOMBRE_BIBLIOTECA(obj[1].toString());
            biblioteca.setDIRECCION(obj[2].toString());
            biblioteca.setEMAIL(obj[3].toString());
            biblioteca.setID_INSTITUCION_MEDIADOR(Integer.parseInt(obj[4].toString()));
            biblioteca.setCODIGO_SNB(obj[5].toString());
            biblioteca.setID_DISTRITO(obj[6].toString());
            biblioteca.setID_PROVINCIA(obj[7].toString());
            biblioteca.setID_REGION(obj[8].toString());
            if (obj[9].equals("1")) {
                biblioteca.setACTIVO(true);
            } else {
                biblioteca.setACTIVO(false);
            }
            biblioteca.setURL(obj[10].toString());
            biblioteca.setDIRECTORIO(obj[11].toString());
            biblioteca.setTITULO_MAPA(obj[12].toString());
            biblioteca.setLATITUD(obj[13].toString());
            biblioteca.setLONGITUD(obj[14].toString());

        }
        return biblioteca;
    }

    @Override
    public ArrayList<Biblioteca> lstMapeoBibliotecas(String idBiblioteca) {
        ArrayList<Biblioteca> lstMapBibliotecas = new ArrayList<>();
        List<Object[]> objBiblioteca;
        String[] parametros = new String[1];
        parametros[0] = idBiblioteca;
        objBiblioteca = conector.execProcedure("BV.SP_BIBLIOTECA_GEOLOCALIZACION", parametros);
        Biblioteca b;
        for (Object[] obj : objBiblioteca) {
            b = new Biblioteca();
            b.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(obj[0].toString()));
            b.setTITULO_MAPA(obj[1].toString());
            b.setLATITUD(obj[2].toString());
            b.setLONGITUD(obj[3].toString());
            lstMapBibliotecas.add(b);

        }
        return lstMapBibliotecas;
    }

    @Override
    public Biblioteca oobtenerServidorBiblioteca(String idBiblioteca) {
        Biblioteca bib = new Biblioteca();
        List<Object[]> objBiblioteca;
        String[] parametros = new String[1];
        parametros[0] = idBiblioteca;
        objBiblioteca = conector.execProcedure("BV.SP_BIBLIOTECA_SERVIDOR", parametros);
        for (Object[] obj : objBiblioteca) {
            bib.setURL(obj[0].toString());
            bib.setDIRECTORIO(obj[1].toString());

        }

        return bib;
    }

}
