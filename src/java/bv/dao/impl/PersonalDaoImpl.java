package bv.dao.impl;

import bv.dao.PersonalDao;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.LogTabla;
import bv.entidad.Personal;
import bv.util.sql;

public class PersonalDaoImpl implements PersonalDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Personal p, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(p.getID_PERSONAL_BIBLIOTECA());
        parametros[1] = p.getNOMBRES();
        parametros[2] = p.getPATERNO();
        parametros[3] = p.getMATERNO();
        parametros[4] = p.getDNI();
        parametros[5] = String.valueOf(p.getID_BIBLIOTECA_MEDIADOR());
        parametros[6] = p.getCARGO();
        parametros[7] = p.getCORREO();
        if (p.isBoolREPRESENTANTE()) {
            parametros[8] = "1";
        } else {
            parametros[8] = "0";
        }
        parametros[9] = "INSERT_PERSONAL_BIBLIOTECA";
        parametros[10] = p.getIdTipoUsuario();
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA_INSERT", parametros);
        for (Object[] dat : result) {
            if (dat[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", "", "1", String.valueOf(idUsuario)));
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo, int idUsuario) {
        int n = 0;
        String[] parametros = new String[10];
        parametros[0] = codigo + "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "DEL_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", "", "3", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Personal p, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(p.getID_PERSONAL_BIBLIOTECA());
        parametros[1] = p.getNOMBRES();
        parametros[2] = p.getPATERNO();
        parametros[3] = p.getMATERNO();
        parametros[4] = p.getDNI();
        parametros[5] = String.valueOf(p.getID_BIBLIOTECA_MEDIADOR());
        parametros[6] = p.getCARGO();
        parametros[7] = p.getCORREO();
        if (p.isBoolREPRESENTANTE()) {
            parametros[8] = "1";
        } else {
            parametros[8] = "0";
        }
        parametros[9] = "UPD_PERSONAL_BIBLIOTECA";
        if (p.isBoolActivo()) {
            parametros[10] = "1";
        } else {
            parametros[10] = "0";
        }
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA_UPDATE", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", "", "2", String.valueOf(idUsuario)));

        }
        return n;

    }

    @Override
    public List<Personal> obtenerEntidades() {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[10];
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = "";
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "LIST_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(true);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;
    }

    @Override
    public Personal buscarEntidad(int codigo) {
        Personal personal = null;
        String[] array = new String[10];
        array[0] = codigo + "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = "";
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "BUSCAR_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setCARGO(datos[6].toString());
            personal.setCORREO(datos[7].toString());
            personal.setREPRESENTANTE(datos[8].toString());
        }
        return personal;
    }

    @Override
    public List<Object[]> obtenerBiblioteca(String idTipoUsuario, String idBibliotecaMediador) {
        String[] parametros = new String[2];
        parametros[0] = idTipoUsuario;
        parametros[1] = idBibliotecaMediador;
        List<Object[]> biliotecas = conector.execProcedure("BV.SP_LISTAR_BIBLIOTECAS", parametros);
        return biliotecas;
    }

    @Override
    public List<Personal> obtenerEntidades(String idBiblioteca) {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[10];
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = idBiblioteca;
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "LIST_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            // personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(false);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;

    }

    @Override
    public List<Personal> obtenerEntidades2(String idBiblioteca, String idTipoUsuario) {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[3];
        array[0] = idBiblioteca;
        array[1] = idTipoUsuario;
        array[2] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_PERSONAL_LISTAR", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            // personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolActivo(true);
            } else {
                personal.setBoolActivo(false);
            }
            if (datos[10].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(false);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;

    }

    @Override
    public String nomBiblioteca(String idBiblioteca) {
        String nombreBilioteca = "";
        String[] array = new String[1];
        array[0] = idBiblioteca;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_BIBLIOTECA_NOMBRE", array);
        for (Object[] datos : data) {
            nombreBilioteca = datos[0].toString();
        }
        return nombreBilioteca;
    }

}
