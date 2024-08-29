package gestor.controlador;

import java.time.LocalDate;

public class Tarea extends Proyecto {

    private String idTarea;
    private String nombreTarea;
    private String responsableTarea;
    private LocalDate fechaDeVencimiento;
    private String prioridad;
    private Boolean finalizadaTarea;
    private String comentario;

    public Tarea() {
        super();
        this.idTarea = "";
        this.nombreTarea = "";
        this.responsableTarea = "";
        this.prioridad = "";
        this.finalizadaTarea = false;
        this.comentario = "";
    }

    public Tarea(String idTarea, String nombreTarea, String responsableTarea, LocalDate fechaDeVencimiento, String prioridad,
            Boolean finalizadaTarea, String comentario, String nombre) {
        super(nombre);
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.responsableTarea = responsableTarea;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.prioridad = prioridad;
        this.finalizadaTarea = finalizadaTarea;
        this.comentario = comentario;
    }

    public Tarea(String idTarea, String nombreTarea, String responsableTarea, LocalDate fechaDeVencimiento, String prioridad,
            Boolean finalizadaTarea, String nombre) {
        super(nombre);
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.responsableTarea = responsableTarea;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.prioridad = prioridad;
        this.finalizadaTarea = finalizadaTarea;
    }

    public Tarea(String idTarea, String nombreTarea, String responsableTarea, LocalDate fechaDeVencimiento) {
        this.idTarea = idTarea;
        this.nombreTarea = nombreTarea;
        this.responsableTarea = responsableTarea;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Tarea(String responsableTarea) {
        this.responsableTarea = responsableTarea;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getResponsableTarea() {
        return responsableTarea;
    }

    public void setResponsableTarea(String responsableTarea) {
        this.responsableTarea = responsableTarea;
    }

    public LocalDate getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Boolean getFinalizadaTarea() {
        return finalizadaTarea;
    }

    public void setFinalizadaTarea(Boolean finalizadaTarea) {
        this.finalizadaTarea = finalizadaTarea;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
