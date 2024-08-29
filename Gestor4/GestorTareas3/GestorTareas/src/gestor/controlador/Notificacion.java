package gestor.controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

public class Notificacion extends Tarea implements Runnable {

    private int diasAntes; //Inicio de las notificaciones
    private int minutosIntervalo; //Constancia de las notficaciones en minutos para poder mostrar el ejemplo en la corrida
    private boolean detener;

    public Notificacion(int diasAntes, int minutosIntervalo, boolean detener, String idTarea, String nombreTarea, String responsableTarea, LocalDate fechaDeVencimiento) {
        super(idTarea, nombreTarea, responsableTarea, fechaDeVencimiento);
        this.diasAntes = diasAntes;
        this.minutosIntervalo = minutosIntervalo;
        this.detener = detener;
    }

    public Notificacion(int diasAntes, int minutosIntervalo, boolean detener, String responsableTarea) {
        super(responsableTarea);
        this.diasAntes = diasAntes;
        this.minutosIntervalo = minutosIntervalo;
        this.detener = detener;
    }

    @Override
    public void run() {
        LocalDate hoy = LocalDate.now();
        LocalDate fechaInicioNotificaciones = getFechaDeVencimiento().minusDays(diasAntes);
        //Primera notificación a la hora que se empiece a correr el sistema para efectos de demostracion
        LocalDateTime siguienteNotificacion = LocalDateTime.now();

        while (!detener && LocalDate.now().isBefore(getFechaDeVencimiento().plusDays(1))) {  //Sumo 1 día para tambien ejecutar durante el día del vencimiento
            hoy = LocalDate.now();

            //Verifico si ya estoy dentro del rango de fechas donde se desea enviar notificaciones
            if (hoy.isAfter(fechaInicioNotificaciones) || hoy.isEqual(fechaInicioNotificaciones)) {
                LocalDateTime ahora = LocalDateTime.now();

                if (!ahora.isBefore(siguienteNotificacion)) {
                    enviarNotificacion();
                    //Programo la siguiente notificación
                    siguienteNotificacion = siguienteNotificacion.plusMinutes(minutosIntervalo);
                }

                //Calculo cuánto tiempo falta para la siguiente notificación en milisegundos con ChronoUnit
                long sleepTime = ChronoUnit.MILLIS.between(LocalDateTime.now(), siguienteNotificacion);
                System.out.println("Tiempo de espera calculado: " + sleepTime + " ms");

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    siguienteNotificacion = siguienteNotificacion.plusMinutes(minutosIntervalo);
                }
            } else {
                //Si la fecha actual aún no es el inicio de las notificaciones, el hilo duerme hasta el inicio
                long sleepTime = ChronoUnit.MILLIS.between(LocalDateTime.now(), LocalDateTime.of(fechaInicioNotificaciones, LocalTime.now()));
                System.out.println("Tiempo de espera calculado: " + sleepTime + " ms");
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //Código de la notificación
    private void enviarNotificacion() {
        //Calculo los días faltantes para el vencimiento
        long diasFaltantes = ChronoUnit.DAYS.between(LocalDate.now(), getFechaDeVencimiento());

        JOptionPane.showMessageDialog(null, "La tarea " + getIdTarea() + ": " + getNombreTarea() + " está próxima a vencer."
                + "\n Vence en " + diasFaltantes + " días.");
    }

    public void detenerNotificaciones() {
        this.detener = true;  // Detener el hilo
    }

    public int getDiasAntes() {
        return diasAntes;
    }

    public void setDiasAntes(int diasAntes) {
        this.diasAntes = diasAntes;
    }

    public int getMinutosIntervalo() {
        return minutosIntervalo;
    }

    public void setMinutosIntervalo(int minutosIntervalo) {
        this.minutosIntervalo = minutosIntervalo;
    }

    public boolean isDetener() {
        return detener;
    }

    public void setDetener(boolean detener) {
        this.detener = detener;
    }
}
