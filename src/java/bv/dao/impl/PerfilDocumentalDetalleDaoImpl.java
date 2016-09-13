package bv.dao.impl;

import bv.dao.PerfilDocumentalDetalleDao;
import java.util.ArrayList;
import java.util.List;
import bv.entidad.LogTabla;
import bv.entidad.PerfilDocumentalDetalle;
import bv.util.sql;
import bv.dto.PerfilDto;

/**
 *
 * @author virtual
 */
public class PerfilDocumentalDetalleDaoImpl implements PerfilDocumentalDetalleDao{

    sql conector = new sql();

    @Override
    public List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalle(String perfil) {
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle = new ArrayList<>();
        PerfilDocumentalDetalle perfilDocumentalDetalle;
        String[] array = new String[1];
        array[0] = perfil;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_PERFIL_DOCUMENTAL_DETALLE", array);
        for (Object[] d : data) {
            perfilDocumentalDetalle = new PerfilDocumentalDetalle();
            perfilDocumentalDetalle.setID_PERFIL_DOCUMENTAL(d[0].toString());
            perfilDocumentalDetalle.setID_PERFIL_DOCUMENTAL_DETALLE(Integer.parseInt(d[1].toString()));
            perfilDocumentalDetalle.setTABLA(d[2].toString());
            perfilDocumentalDetalle.setCAMPO(d[3].toString());
            perfilDocumentalDetalle.setStrVista(d[4].toString());
            perfilDocumentalDetalle.setStrRequerido(d[5].toString());
            if (d[4].toString().equals("1")) {
                perfilDocumentalDetalle.setVISTA(true);
            } else {
                perfilDocumentalDetalle.setVISTA(false);
            }
            if (d[5].toString().equals("1")) {
                perfilDocumentalDetalle.setREQUERIDO(true);
            } else {
                perfilDocumentalDetalle.setREQUERIDO(false);
            }
            lstPerfilDocumentalDetalle.add(perfilDocumentalDetalle);
        }
        return lstPerfilDocumentalDetalle;
    }

    @Override
    public List<Object[]> obtenerPerfiles() {
        String[] parametros = new String[1];
        parametros[0] = "LISTAR_PERFILES";
        List<Object[]> lstPerfiles = conector.execProcedure("BV.SP_LISTAR_PERFIL", parametros);
        return lstPerfiles;

    }

    @Override
    public int editarListPerfildocumentaldetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle, int idUsuario) {
        int cont = 0;
        for (PerfilDocumentalDetalle p : lstPerfilDocumentalDetalle) {
            String[] parametros = new String[5];
            parametros[0] = String.valueOf(p.getID_PERFIL_DOCUMENTAL());
            parametros[1] = String.valueOf(p.getID_PERFIL_DOCUMENTAL_DETALLE());
            if (p.isVISTA()) {
                parametros[2] = "1";
            } else {
                parametros[2] = "0";
            }
            if (p.isREQUERIDO()) {
                parametros[3] = "1";
            } else {
                parametros[3] = "0";
            }
            parametros[4] = "EDITAR";
            ArrayList<Object[]> result = conector.execProcedure("[BV].[SP_MANTENIMIENTO_PERFIL_DOCUMENTAL_DETALLE]", parametros);
            if (result != null) {
                int n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERFIL_DOCUMENTAL_DETALLE", "", "2", String.valueOf(idUsuario)));
                cont = cont + n;
            }
        }
        return cont;
    }

    

    @Override
    public PerfilDto obtenerPerfilXidDocumental(String idDocumental) {
        PerfilDto pDto=new PerfilDto();
        String[] array = new String[1];
        array[0] = idDocumental;
       ArrayList<Object[]> data = conector.execProcedure("BV.SP_GET_idPerfil_Perfil", array);
       
     for(Object[] d : data ){
         pDto.setID_perfil(d[0].toString());
         pDto.setPerfil(d[1].toString());
     }
        
        return pDto;
    }

}
