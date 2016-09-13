package bv.entidad;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class PerfilDocumental {

    private int ID_PERFIL_DOCUMENTAL;
    private String PERFIL_DOCUMENTAL;
    private String DESCRIPCION;
    private int ESTADO;
    private String ICONO;

    public int getID_PERFIL_DOCUMENTAL() {
        return ID_PERFIL_DOCUMENTAL;
    }

    public void setID_PERFIL_DOCUMENTAL(int ID_PERFIL_DOCUMENTAL) {
        this.ID_PERFIL_DOCUMENTAL = ID_PERFIL_DOCUMENTAL;
    }

    public String getPERFIL_DOCUMENTAL() {
        return PERFIL_DOCUMENTAL;
    }

    public void setPERFIL_DOCUMENTAL(String PERFIL_DOCUMENTAL) {
        this.PERFIL_DOCUMENTAL = PERFIL_DOCUMENTAL;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getESTADO() {
        return ESTADO;
    }

    public void setESTADO(int ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getICONO() {
        return ICONO;
    }

    public void setICONO(String ICONO) {
        this.ICONO = ICONO;
    }

}
