package bv.bean;

import bv.dao.PersonalDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.PERSONAL;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import bv.entidad.Personal;

/**
 *
 * @author virtual
 */
@ManagedBean(name = "personalB")
@ViewScoped
public class personalBibliotecaBean implements Serializable {

    private final PersonalDao personalDao;
    private Personal personal;
    private Personal personalUpd;
    private List<Personal> filterListPersonal;
    private List<Personal> lstPersonal;
    private List<SelectItem> cboBiblioteca;
    private final FacesContext faceContext;
    int idUsuario;

    public personalBibliotecaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        personalDao = factory.getPersonalDao(PERSONAL);
        personal = new Personal();
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        String idTipoUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString();
        lstPersonal = personalDao.obtenerEntidades2(idBiblioteca, idTipoUsuario);
        faceContext = FacesContext.getCurrentInstance();
        personalUpd = new Personal();
        idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
    }

    public List<Personal> getFilterListPersonal() {
        return filterListPersonal;
    }

    public void setFilterListPersonal(List<Personal> filterListPersonal) {
        this.filterListPersonal = filterListPersonal;
    }

    public Personal getPersonalUpd() {
        return personalUpd;
    }

    public void setPersonalUpd(Personal personalUpd) {
        this.personalUpd = personalUpd;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<SelectItem> getCboBiblioteca() {
        String idTipoUsuario = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario"));
        String idBibliotecaMediador = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
        List<Object[]> lista = personalDao.obtenerBiblioteca(idTipoUsuario, idBibliotecaMediador);
        cboBiblioteca = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboBiblioteca.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }

        return cboBiblioteca;
    }

    public void setCboBiblioteca(List<SelectItem> cboBiblioteca) {
        this.cboBiblioteca = cboBiblioteca;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public void crearPersonal() {
        int dataInsert = personalDao.crearEntidad(personal, idUsuario);
        if (dataInsert == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Completada", "Encargado registrado con éxito."));
        }
        limpiar();
    }

    public void updPersonalBiblioteca() {

        int dataUpdate = personalDao.actualizarEntidad(personalUpd, idUsuario);//bibliotecaDao.actualizarEntidad(bibliotecaUpd);
        RequestContext rc = RequestContext.getCurrentInstance();
        if (dataUpdate == 1) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro editado correctamente."));
            rc.execute("PF('tblPersonal').clearFilters();");
            rc.execute("PF('dlbUpdPersonal').hide();");
        } else {
            rc.execute("PF('tblPersonal').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo editar este registro."));
        }
    }

    public void delPersoanlBiblioteca(Personal p) throws IOException {
        RequestContext rc = RequestContext.getCurrentInstance();
        int dataDelete = personalDao.eliminarEntidad(p.getID_PERSONAL_BIBLIOTECA(), idUsuario);
        if (dataDelete == 1) {
            rc.execute("PF('tblPersonal').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro eliminado correctamente."));
            limpiar();
        } else {
            rc.execute("PF('tblPersonal').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar este registro."));
        }

    }

    public void limpiar() {
        personal.setNOMBRES("");
        personal.setMATERNO("");
        personal.setPATERNO("");
        personal.setDNI("");
        personal.setID_BIBLIOTECA_MEDIADOR(-1);
        personal.setBoolREPRESENTANTE(false);
        personal.setCARGO("");
        personal.setCORREO("");

    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context
                .getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
    }
}
