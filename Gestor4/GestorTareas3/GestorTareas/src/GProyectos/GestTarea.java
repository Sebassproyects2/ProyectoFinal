package GProyectos;

import gestor.modelo.ConexionBD;
import gestor.controlador.Tarea;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.*;

public class GestTarea {

    public void insertarTarea(Tarea tarea) {
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement misql = con.crearPreparedStatement("INSERT INTO tareas VALUES (?,?,?,?,?,?,?,?)");
            misql.setString(1, tarea.getIdTarea());
            misql.setString(2, tarea.getNombreProyecto());
            misql.setString(3, tarea.getNombreTarea());
            misql.setString(4, tarea.getResponsableTarea());
            misql.setDate(5, Date.valueOf(tarea.getFechaDeVencimiento()));
            misql.setString(6, tarea.getPrioridad());
            misql.setBoolean(7, tarea.getFinalizadaTarea());
            misql.setString(8, tarea.getComentario());
            //3-ejecutar el comento sql
            misql.executeUpdate();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<Tarea> todosTareas() {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            Statement st = con.crearStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tareas");
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("Prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    public boolean eliminarTarea(Tarea tarea) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "DELETE FROM tareas WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setString(1, tarea.getIdTarea());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean modificarTarea(Tarea tarea) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "UPDATE tareas SET proyecto=?, nombreTarea=?, responsableTarea=?,"
                + "fechaDeVencimiento=?, prioridad=?, finalizadaTarea=?,"
                + "comentarios=? WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setString(1, tarea.getNombreProyecto());
            ps.setString(2, tarea.getNombreTarea());
            ps.setString(3, tarea.getResponsableTarea());
            ps.setDate(4, Date.valueOf(tarea.getFechaDeVencimiento()));
            ps.setString(5, tarea.getPrioridad());
            ps.setBoolean(6, tarea.getFinalizadaTarea());
            ps.setString(7, tarea.getComentario());
            ps.setString(8, tarea.getIdTarea());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean reAsignarTarea(Tarea tarea) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "UPDATE tareas SET responsableTarea=? WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setString(1, tarea.getResponsableTarea());
            ps.setString(2, tarea.getIdTarea());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean finalizarTarea(Tarea tarea) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "UPDATE tareas SET finalizadaTarea=? WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setBoolean(1, true);
            ps.setString(2, tarea.getIdTarea());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean agregarComentario(Tarea tarea) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "UPDATE tareas SET comentarios=? WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setString(1, tarea.getComentario());
            ps.setString(2, tarea.getIdTarea());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    //Busquedas por todos las distintas variables de las tareas
    //Busqueda por id de la tarea
    public ArrayList<Tarea> buscarTareaId(String idTarea) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE id=?");
            st.setString(1, idTarea);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda por responsable de la tarea
    public ArrayList<Tarea> buscarTareaResponsable(String responsable) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE responsableTarea=?");
            st.setString(1, responsable);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda por nombre del proyecto    
    public ArrayList<Tarea> buscarTareaNombreProyecto(String nombre) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE proyecto=?");
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda por fecha de vencimiento de la tarea
    public ArrayList<Tarea> buscarTareaFechaVencimiento(LocalDate fechaDeVencimiento) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE fechaDeVencimiento=?");
            st.setDate(1, Date.valueOf(fechaDeVencimiento));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda por prioridad de la tarea
    public ArrayList<Tarea> buscarTareaPrioridad(String prioridad) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE prioridad=?");
            st.setString(1, prioridad);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda de tareas por estado de finalización
    public ArrayList<Tarea> buscarTareaFinalizada(boolean finalizada) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE finalizadaTarea=?");
            st.setBoolean(1, finalizada);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    //Busqueda por prioridad de la tarea
    public ArrayList<Tarea> buscarTareaNombre(String nombre) {
        ArrayList<Tarea> miListaTarea = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE nombreTarea like ?");
            nombre = '%' + nombre + '%';
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tarea tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
                miListaTarea.add(tarea);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaTarea;
    }

    public Tarea encontrarTareaId(String idTarea) {
        Tarea tarea = null;
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM tareas WHERE id=?");
            st.setString(1, idTarea);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                tarea = new Tarea(rs.getString("id"), rs.getString("nombreTarea"),
                        rs.getString("responsableTarea"), rs.getDate("fechaDeVencimiento").toLocalDate(),
                        rs.getString("prioridad"), rs.getBoolean("finalizadaTarea"),
                        rs.getString("comentarios"), rs.getString("proyecto"));
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestTarea.class.getName()).log(Level.SEVERE, null, e);
        }
        return tarea;
    }

}
