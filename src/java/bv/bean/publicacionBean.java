package bv.bean;

import bv.dao.PerfilDocumentalDetalleDao;
import bv.dao.PublicacionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.PERFIL_DOCUMENTAL_DETALLE;
import static bv.util.Constantes.PUBLICACION;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import bv.dto.PublicacionDto;
import bv.entidad.Documental;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class publicacionBean {

    /**
     * Creates a new instance of publicacionBean
     */
    private final PerfilDocumentalDetalleDao pddDao;
    private List<PublicacionDto> lpublicacion = new ArrayList<>();
    private final PublicacionDao pubDao;
    String perfilControl;
    private List<SelectItem> cboPerfiles;
    String visibilidad;
    private List<SelectItem> cboVisibilidad;
//-------------
    private Documental dgn = new Documental();
    public publicacionBean() {
        DaoFactory factory = DaoFactory.getInstance();
        pubDao = factory.getPublicacionDao(PUBLICACION);
        pddDao = factory.getPerfilDocumentalDetalleDao(PERFIL_DOCUMENTAL_DETALLE);
        listarDocumentalPublicado();
    }

    public Documental getDgn() {
        return dgn;
    }

    public void setDgn(Documental dgn) {
        this.dgn = dgn;
    }

    
    public List<PublicacionDto> getLpublicacion() {
        return lpublicacion;
    }

    public void setLpublicacion(List<PublicacionDto> lpublicacion) {
        this.lpublicacion = lpublicacion;
    }

    public String getPerfilControl() {
        return perfilControl;
    }

    public void setPerfilControl(String perfilControl) {
        this.perfilControl = perfilControl;
    }

    public List<SelectItem> getCboPerfiles() {
        List<Object[]> lista = pddDao.obtenerPerfiles();
        cboPerfiles = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPerfiles.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }

        return cboPerfiles;
    }

    public void setCboPerfiles(List<SelectItem> cboPerfiles) {
        this.cboPerfiles = cboPerfiles;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public List<SelectItem> getCboVisibilidad() {
        cboVisibilidad= new ArrayList<>();
        cboVisibilidad.add(new SelectItem("1","Publicado"));
        cboVisibilidad.add(new SelectItem("0","No Publicado"));
        return cboVisibilidad;
    }

    public void setCboVisibilidad(List<SelectItem> cboVisibilidad) {
        this.cboVisibilidad = cboVisibilidad;
    }

    public void listarDocumentalPublicado() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
       if(perfilControl==null){
       perfilControl="-1";}
       if(visibilidad==null){
       visibilidad="-1";}
        lpublicacion = pubDao.listPublicacion(perfilControl, idBiblioteca, visibilidad);
        RequestContext.getCurrentInstance().update("frmPub:tblPub");
    }

    public void cambiaVisiblidad(PublicacionDto pub) {
        int update = pubDao.cambiaVisibilidad(pub);
        listarDocumentalPublicado();
    }
    
    
    

}
