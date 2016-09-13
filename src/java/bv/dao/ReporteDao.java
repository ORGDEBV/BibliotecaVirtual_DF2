package bv.dao;

import java.util.ArrayList;
import java.util.List;
import bv.entidad.reporteUsuarioDocumental;

/**
 *
 * @author virtual
 */
public interface ReporteDao {

    List<Object[]> listUsuarioDocumental(String date, String idBiblioteca);

    List<reporteUsuarioDocumental> obtenerEntidades(String date, String idBiblioteca);

    List<Object[]> listPeriodoTrabajadores(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca);

    ArrayList<Object[]> listaConsolidadoMensual(int Anio, int idBiblioteca);

    ArrayList<Object[]> listaUsusariosMensual(int Anio, int idBiblioteca);

    int conteoMaxMensual(int Anio, int idBiblioteca);

    List<reporteUsuarioDocumental> obtenerPorPeriodo(int ano, int fecini, int fecfin, int idUsuario, int TipoUsuario, String idBiblioteca);

    List<Object[]> listaInserUpdTotales(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca);

}
