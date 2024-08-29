package GProyectos;

import gestor.controlador.Proyecto;
import gestor.modelo.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.*;

public class GestProyecto {

    public void insertarProyecto(Proyecto proyecto) {
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement misql = con.crearPreparedStatement("INSERT INTO proyectos VALUES (?,?,?,?,?,?)");
            misql.setInt(1, proyecto.getIdProyecto());
            misql.setString(2, proyecto.getNombreProyecto());
            misql.setString(3, proyecto.getPersonaResp());
            misql.setDate(4, Date.valueOf(proyecto.getFechaInicio()));
            misql.setDate(5, Date.valueOf(proyecto.getFechaFinal()));
            misql.setBoolean(6, proyecto.isFinalizada());
            //3-ejecutar el comento sql
            misql.executeUpdate();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<Proyecto> todosProyecto() {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            Statement st = con.crearStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proyectos");
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }
    
    public ArrayList<String> todosNombreProyecto() {
        ArrayList<String> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            Statement st = con.crearStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proyectos");
            while (rs.next()) {
                String proyecto = rs.getString("id") + " " + rs.getString("nombre");
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    public boolean eliminarProyecto(Proyecto proyecto) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "DELETE FROM proyectos WHERE id=? ";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setInt(1, proyecto.getIdProyecto());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public boolean modificarProyecto(Proyecto proyecto) {
        PreparedStatement ps = null;
        ConexionBD con = new ConexionBD();

        String sql = "UPDATE proyectos SET nombre=?, personaResp=?, fechaInicio=?, fechaFinal=?, finalizada=? WHERE id=?";

        try {
            ps = con.crearPreparedStatement(sql);
            ps.setString(1, proyecto.getNombreProyecto());
            ps.setString(2, proyecto.getPersonaResp());
            ps.setDate(3, Date.valueOf(proyecto.getFechaInicio()));
            ps.setDate(4, Date.valueOf(proyecto.getFechaFinal()));
            ps.setBoolean(5, proyecto.isFinalizada());
            ps.setInt(6, proyecto.getIdProyecto());
            ps.execute();
            con.cerrarConexion();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    //Busquedas por todos las distintas variables de los proyectos 
    
    //Busqueda por persona responsable del proyecto
    public ArrayList<Proyecto> buscarProyectoResponsable(String personaResp) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE personaResp=?");
            st.setString(1, personaResp);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    //Busqueda por nombre del proyecto
    public ArrayList<Proyecto> buscarProyectoNombre(String nombre) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE nombre like ?");
            nombre = '%' + nombre + '%';
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    //Busqueda por id del proyecto
    public ArrayList<Proyecto> buscarProyectoId(int idProyecto) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE id=?");
            st.setInt(1, idProyecto);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                    Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                    miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    //Busqueda de proyectos por estado de finalización
    public ArrayList<Proyecto> buscarProyectoFinalizada(boolean finalizada) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE finalizada=?");
            st.setBoolean(1, finalizada);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    //Busqueda por fecha final del proyecto
    public ArrayList<Proyecto> buscarProyectoFechaFinal(LocalDate fechaFinal) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE fechaFinal=?");
            st.setDate(1, Date.valueOf(fechaFinal));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

    //Busqueda por fecha inicio del proyecto
    public ArrayList<Proyecto> buscarProyectoFechaInicio(LocalDate fechaInicio) {
        ArrayList<Proyecto> miListaProyecto = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM proyectos WHERE fechaInicio=?");
            st.setDate(1, Date.valueOf(fechaInicio));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("personaResp"), rs.getDate("fechaInicio").toLocalDate(), rs.getDate("FechaFinal").toLocalDate(),
                        rs.getBoolean("finalizada"));
                miListaProyecto.add(proyecto);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(GestProyecto.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaProyecto;
    }

}
