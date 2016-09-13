package bv.dao;

import java.util.List;

/**
 *
 * @author virtual
 */
public interface TipoDao {

    List<Object[]> llenaComboTipo1();

    List<Object[]> llenaComboTipoXperfil(String perfil);

}
