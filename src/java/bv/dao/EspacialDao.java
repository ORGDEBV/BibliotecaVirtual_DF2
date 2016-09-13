package bv.dao;

import java.util.List;
import bv.entidad.Cobertura;

/**
 *
 * @author virtual
 */
public interface EspacialDao {

    int CrearCobertura(Cobertura cobertura);

    int editarCobertura(Cobertura cobertura);

    List<Cobertura> obtenerCobertura();

    List<Object[]> llenaComboCobertura(String idBiblioteca);

    List<Object[]> llenaComboPais();

    Cobertura obtenerXidCoberturaEspacial(String idCoberturaespacial);

}
