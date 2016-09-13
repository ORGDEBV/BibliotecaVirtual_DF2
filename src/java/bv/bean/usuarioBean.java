package bv.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import bv.dao.LoginDao;
import bv.dao.PersonalDao;
import bv.dao.UsuarioDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.LOGIN;
import static bv.util.Constantes.PERSONAL;
import static bv.util.Constantes.USUARIO;
import bv.entidad.Personal;
import bv.entidad.Usuario;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@ViewScoped
public class usuarioBean implements Serializable {
    
    private Boolean Representante;
    private Boolean Administrador;
    private Usuario usuario;
    private Personal personal;
    private final LoginDao loginDao;
    private UsuarioDao usuarioDao;
    Usuario usuarioUpd;
    private List<Usuario> filterListUsuario;
    private List<Usuario> ListUsuario;
    private List<SelectItem> cboTipoUsuario;
    private final PersonalDao personalDao;
    private int valor;
    String nom_Biblioteca;
    String tipoUsuario;
    
    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public usuarioBean() {
        DaoFactory factory = DaoFactory.getInstance();
        loginDao = factory.getLoginDao(LOGIN);
        personalDao = factory.getPersonalDao(PERSONAL);
        usuario = new Usuario();
        usuarioUpd = new Usuario();
        usuarioDao = factory.getUsuarioDao(USUARIO);
        personal = new Personal();
    }
    
    public String getNom_Biblioteca() {
        return nom_Biblioteca;
    }
    
    public void setNom_Biblioteca(String nom_Biblioteca) {
        this.nom_Biblioteca = nom_Biblioteca;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public List<SelectItem> getCboTipoUsuario() {
        String idTipoUsuario = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario"));
        List<Object[]> lista = usuarioDao.obtenerTipousuario(idTipoUsuario, idTipoUsuario, idTipoUsuario);
        cboTipoUsuario = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboTipoUsuario.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboTipoUsuario;
    }
    
    public void setCboTipoUsuario(List<SelectItem> cboTipoUsuario) {
        this.cboTipoUsuario = cboTipoUsuario;
    }
    
    public List<Usuario> getListUsuario() {
        String idTipoUsuario = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario"));
        String idBibliotecaMediador = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
        String idPersonalBiblioteca = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdPersonalBiblioteca"));
        ListUsuario = usuarioDao.obtenerEntidadesParametros(idTipoUsuario, idBibliotecaMediador, idPersonalBiblioteca);
        return ListUsuario;
    }
    
    public void updUsuario() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        int dataUpdate = usuarioDao.actualizarEntidad(usuarioUpd, idUsuario);
        RequestContext rc = RequestContext.getCurrentInstance();
        if (dataUpdate == 1) {
            //FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro editado correctamente."));
            rc.execute("PF('tblUsuario').clearFilters();");
            rc.execute("PF('dlbUpdUsuario').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro editado correctamente."));
        } else {
            rc.execute("PF('tblUsuario').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo editar este registro."));
        }
    }
    
    public void resetContrasena() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String idTipoUsuario = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario"));
        RequestContext rc = RequestContext.getCurrentInstance();
        if (idTipoUsuario.equals("1") && usuarioUpd.getCAMBIO_PASSW_SISTEMA() == 1) {
            int dataUpdate = usuarioDao.resetContrasena(usuarioUpd, idUsuario);
            if (dataUpdate == 1) {
                rc.execute("PF('tblUsuario').clearFilters();");
                rc.execute("PF('dlbUpdUsuario').hide();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Contraseña de usuario reseteada correctamente."));
            }
        } else if (idTipoUsuario.equals("2") && usuarioUpd.getCAMBIO_PASSW_SISTEMA() == 1) {
            int dataUpdate = usuarioDao.resetContrasena(usuarioUpd, idUsuario);
            if (dataUpdate == 1) {
                rc.execute("PF('tblUsuario').clearFilters();");
                rc.execute("PF('dlbUpdUsuario').hide();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Contraseña de usuario reseteada correctamente."));
            }
        } else {
            rc.execute("PF('tblUsuario').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No tiene los permisos o la contraseña ya fue reseteada."));
        }
        
    }
    
    public void setListUsuario(List<Usuario> ListUsuario) {
        
        this.ListUsuario = ListUsuario;
    }
    
    public Usuario getUsuarioUpd() {
        return usuarioUpd;
    }
    
    public void setUsuarioUpd(Usuario usuarioUpd) {
        this.usuarioUpd = usuarioUpd;
    }
    
    public List<Usuario> getFilterListUsuario() {
        return filterListUsuario;
    }
    
    public void setFilterListUsuario(List<Usuario> filterListUsuario) {
        this.filterListUsuario = filterListUsuario;
    }
    
    public void loginUsuario() throws IOException {
        usuario = loginDao.validar(usuario.getUSUARIO(), usuario.getCONTRASENA());
        
        if (usuario != null) {
            personal = personalDao.buscarEntidad(usuario.getID_PERSONAL_BIBLIOTECA());
            String idBiblioteca = String.valueOf(personal.getID_BIBLIOTECA_MEDIADOR());
            nom_Biblioteca = personalDao.nomBiblioteca(idBiblioteca);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalIdUsuario", usuario.getID_USUARIO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalUsuario", usuario.getUSUARIO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalContrasena", usuario.getCONTRASENA());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalTipoUsuario", usuario.getID_TIPO_USUARIO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalNombres", personal.getNOMBRES());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalPaterno", personal.getPATERNO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalMaterno", personal.getMATERNO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalDni", personal.getDNI());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalidBibliotecaFuente", personal.getID_BIBLIOTECA_MEDIADOR());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalCargo", personal.getCARGO());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalRepresentante", personal.getREPRESENTANTE());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalIdPersonalBiblioteca", personal.getID_PERSONAL_BIBLIOTECA());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cambioContrasena", usuario.getCAMBIO_PASSW_SISTEMA());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreBiblioteca", nom_Biblioteca);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipoUsuario", usuario.getTipoUsuario());
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", personal.getNOMBRES()));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/principal");
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario y/o Contraseña incorrecta."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/Error/AccesoDenegado");
            
        }
        
    }
    
    public void recuperaUsuario() {
        int Recuperar = loginDao.recuperar("");
        if (Recuperar != 0) {
            JOptionPane.showMessageDialog(null, "Correo enviado :)");
        } else {
            JOptionPane.showMessageDialog(null, "El correo no esta registrado :(");
        }
    }
    
    public void validaLogin() throws IOException {
        nom_Biblioteca = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreBiblioteca");
        tipoUsuario = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipoUsuario");
        String user = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalUsuario");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (user == null) {
            ec.redirect("/BibliotecaVirtual/Error/AccesoDenegado");
        } else {
            String nombres = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalNombres");
            String cargo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalCargo");
            personal.setNOMBRES(nombres);
            personal.setCARGO(cargo);
            int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
            if (tipoUsuario == 1) {
                setAdministrador(true);
                setRepresentante(true);
            } else if (tipoUsuario == 2) {
                setAdministrador(false);
                setRepresentante(true);
            }
            int cambioContrasena = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cambioContrasena").toString());
            if (cambioContrasena == 0) {
                FacesContext.getCurrentInstance().addMessage("gMsj", new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No ha actualizado su contraseña por defecto."));
            }
        }
    }
    
    public void cierraSesion() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/");
        int cambioContrasena = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cambioContrasena").toString());
        if (cambioContrasena == 0) {
            FacesContext.getCurrentInstance().addMessage("gMsj", new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", "No ha actualizado su contraseña por defecto."));
        }
    }
    
    public void cambiaContrasena() {
        
        if (usuario.getCONTRASENA_NUEVA1().equals(usuario.getCONTRASENA_NUEVA2())) {
            String contrasena = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalContrasena");
            if (usuario.getCONTRASENA_ANT().equals(contrasena)) {
                int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
                usuario.setID_USUARIO(idUsuario);
                int upd = usuarioDao.cambiarContrasena(usuario, idUsuario);
                if (upd == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Su contraseña ha sido actualizada."));
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personalContrasena", usuario.getCONTRASENA_NUEVA1());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cambioContrasena", '1');
                    
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al intentar cambiar su contraseña."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña actual no coincide con la contraseña ingresada."));
            }
            usuario = new Usuario();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas ingresadas no son iguales."));
            usuario = new Usuario();
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Personal getPersonal() {
        return personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    
    public Boolean getRepresentante() {
        return Representante;
    }
    
    public void setRepresentante(Boolean Representante) {
        this.Representante = Representante;
    }
    
    public Boolean getAdministrador() {
        return Administrador;
    }
    
    public void setAdministrador(Boolean Administrador) {
        this.Administrador = Administrador;
    }
    
}
