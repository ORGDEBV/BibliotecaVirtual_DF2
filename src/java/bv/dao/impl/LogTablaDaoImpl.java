package bv.dao.impl;

import bv.dao.LogTablaDao;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.LogTabla;
import bv.util.sql;

/**
 *
 * @author virtual
 */
public class LogTablaDaoImpl implements LogTablaDao {

    sql conector = new sql();

    @Override
    public boolean registrarLogTabla(LogTabla lt) {
        boolean flag = false;
        String[] parametros = new String[5];
        parametros[0] = lt.getNameEsquema();
        parametros[1] = lt.getNameTabla();
        parametros[2] = lt.getID_REGISTRO();
        parametros[3] = lt.getID_ACCION();
        parametros[4] = lt.getID_USUARIO();
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_TABLA_REGISTRAR]", parametros);
        for (Object[] dato : datos) {
            flag = dato[0].toString().equals("1");
        }
        return flag;
    }

    @Override
    public List<Object[]> listarLog() {
        List<Object[]> lst = new ArrayList<>();
        String[] parametros = new String[1];
        parametros[0] = "LISTAR";
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);
        }
        return lst;
    }

    @Override
    public List<Object[]> listarLogDocumental() {
        List<Object[]> lst = new ArrayList<>();
        String[] parametros = new String[1];
        parametros[0] = "LISTAR_HISTORIAL_DOCUMENTAL";
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);
        }
        return lst;
    }

}
