package bv.entidad;

/**
 *
 * @author virtual
 */
public class LogTabla {

    private Integer ID_LOG;
    private String ID_TABLA;
    private String ID_REGISTRO;
    private String ID_ACCION;
    private String ID_USUARIO;
    private String FECHA;
    // variables auxiliares
    private String nameTabla;
    private String nameEsquema;

    public LogTabla(String nameEsquema, String nameTabla, String ID_REGISTRO, String ID_ACCION, String ID_USUARIO) {
        this.ID_REGISTRO = ID_REGISTRO;
        this.ID_ACCION = ID_ACCION;
        this.ID_USUARIO = ID_USUARIO;
        this.nameTabla = nameTabla;
        this.nameEsquema = nameEsquema;
    }
    

    
    
    public LogTabla() {
    }

    public String getNameTabla() {
        return nameTabla;
    }

    public void setNameTabla(String nameTabla) {
        this.nameTabla = nameTabla;
    }

    public String getNameEsquema() {
        return nameEsquema;
    }

    public void setNameEsquema(String nameEsquema) {
        this.nameEsquema = nameEsquema;
    }

  

    public Integer getID_LOG() {
        return ID_LOG;
    }

    public void setID_LOG(Integer ID_LOG) {
        this.ID_LOG = ID_LOG;
    }

    public String getID_TABLA() {
        return ID_TABLA;
    }

    public void setID_TABLA(String ID_TABLA) {
        this.ID_TABLA = ID_TABLA;
    }

    public String getID_REGISTRO() {
        return ID_REGISTRO;
    }

    public void setID_REGISTRO(String ID_REGISTRO) {
        this.ID_REGISTRO = ID_REGISTRO;
    }

    public String getID_ACCION() {
        return ID_ACCION;
    }

    public void setID_ACCION(String ID_ACCION) {
        this.ID_ACCION = ID_ACCION;
    }

    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
    
    

}
