package bv.dao.impl;

import bv.dao.AutorDao;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.Autor;
import bv.entidad.LogTabla;
import bv.util.sql;

public class AutorDaoImpl implements AutorDao {

    sql conector = new sql();

    @Override
    public List<Autor> obtenerEntidades(String idBiblioteca) {
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "LIST_AUTOR";
        parametros[6] = idBiblioteca;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        ArrayList<Autor> lista = new ArrayList<>();
        Autor aut;
        for (Object[] dato : listaIn) {
            aut = new Autor();
            aut.setID_AUTOR(Integer.parseInt(dato[0].toString()));
            aut.setNOMBRE(dato[1].toString());
            aut.setAPELLIDO_PATERNO(dato[2].toString());
            aut.setAPELLIDO_MATERNO(dato[3].toString());
            aut.setID_PAIS(dato[4].toString());
            aut.setPAIS(dato[5].toString());
            lista.add(aut);
        }
        return lista;
    }

    @Override
    public int crearEntidad(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "ADD_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = codigo + "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "DEL_AUTOR";
        parametros[6] = "";
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "3", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = String.valueOf(aut.getID_AUTOR());
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "UPD_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "2", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int buscarEntidad(Autor aut) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "FIND_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int buscarEntidadAlternativo(Autor aut) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = aut.getID_PAIS();
        parametros[2] = "FIND_ALTERNATIVO";
        parametros[3] = aut.getID_BIBLIOTECA_REGISTRO();
        parametros[4] = aut.getALTERNATIVO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR_ALTERNATIVO", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int crearEntidadAlternativo(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = aut.getID_PAIS();
        parametros[2] = "ADD_AUTOR_ALTERNATIVO";
        parametros[3] = aut.getID_BIBLIOTECA_REGISTRO();
        parametros[4] = aut.getALTERNATIVO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR_ALTERNATIVO", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public List<Object[]> cboAutores(String idBiblioteca) {
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "LIST_CBOAUTOR";
        parametros[6] = idBiblioteca;
        ArrayList<Object[]> lista = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        return lista;
    }

    @Override
    public ArrayList<String> listAutorDocumentallXidDocumental(String idDocumental) {
        ArrayList<String> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_AUTOR_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String obj = d[0].toString();
            lst.add(obj);
        }
        return lst;

    }

    @Override
    public List<Autor> listarAutorIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_AUTOR_ID_DOCUMENTAL", parametros);
        ArrayList<Autor> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Autor aut = new Autor();
            aut.setID_AUTOR(Integer.parseInt(dato[0].toString()));
            aut.setNOMBRE(dato[1].toString());
            aut.setAPELLIDO_PATERNO(dato[2].toString());
            aut.setAPELLIDO_MATERNO(dato[3].toString());
            aut.setID_PAIS(dato[4].toString());
            aut.setPAIS(dato[5].toString());
            lista.add(aut);
        }
        return lista;
    }

}
