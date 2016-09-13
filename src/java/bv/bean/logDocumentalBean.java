package bv.bean;

import bv.dao.LogTablaDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.LOG_TABLA;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class logDocumentalBean {

    private List<Object[]> lstLogTabla;
    private List<Object[]> lstLogTablaFilter;
    private final LogTablaDao log;

    public logDocumentalBean() {
        DaoFactory factory = DaoFactory.getInstance();
        log = factory.getLogTablaDao(LOG_TABLA);
        lstLogTabla = log.listarLogDocumental();
    }

    public List<Object[]> getLstLogTabla() {
        return lstLogTabla;
    }

    public void setLstLogTabla(List<Object[]> lstLogTabla) {
        this.lstLogTabla = lstLogTabla;
    }

    public List<Object[]> getLstLogTablaFilter() {
        return lstLogTablaFilter;
    }

    public void setLstLogTablaFilter(List<Object[]> lstLogTablaFilter) {
        this.lstLogTablaFilter = lstLogTablaFilter;
    }

}
