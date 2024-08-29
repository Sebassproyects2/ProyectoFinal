package gestor.controlador;

import java.time.LocalDate;

public class Proyecto {

    private int idProyecto;
    private String nombreProyecto;
    private String personaResp;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private boolean finalizada;

    public Proyecto() {
        this.idProyecto = -1;
        this.nombreProyecto = "";
        this.personaResp = "";
        this.fechaInicio = null;
        this.fechaFinal = null;
        this.finalizada = false;
    }

    public Proyecto(String nombre) {
        this.nombreProyecto = nombre;
    }

    public Proyecto(int idProyecto, String Nombre, String personaResp, LocalDate fechaInicio, LocalDate fechaFinal, boolean finalizada) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = Nombre;
        this.personaResp = personaResp;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.finalizada = finalizada;
    }

    /**
     * @return the nombreProyecto
     */
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    /**
     * @param nombreProyecto the nombreProyecto to set
     */
    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    /**
     * @return the personaResp
     */
    public String getPersonaResp() {
        return personaResp;
    }

    /**
     * @param personaResp the personaResp to set
     */
    public void setPersonaResp(String personaResp) {
        this.personaResp = personaResp;
    }

    /**
     * @return the fechaInicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFinal
     */
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the finalizada
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * @param finalizada the finalizada to set
     */
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    /**
     * @return the idProyecto
     */
    public int getIdProyecto() {
        return idProyecto;
    }

    /**
     * @param idProyecto the idProyecto to set
     */
    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

}
