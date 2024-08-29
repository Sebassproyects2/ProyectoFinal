package GProyectos;

import gestor.controlador.Notificacion;
import gestor.controlador.Usuario;
import gestor.modelo.ConexionBD;
import java.sql.*;

public class GestNotificacion {

    //Método para insertar la notificacion en la BD cuando se crea el usuario
    //las preferencias van a ser las default
    public void insertarPreferencia(Usuario usuario) {
        String usuarioTxt = usuario.getNombre() + " (" + usuario.getEmail() + ")";
        try {
            ConexionBD con = new ConexionBD();
            String sql = "INSERT INTO notificaciones VALUES (?,?,?,?)";
            PreparedStatement ps = con.crearPreparedStatement(sql);
            ps.setString(1, usuarioTxt);
            ps.setInt(2, 5); //default es 5 diasAntes
            ps.setInt(3, 180);//default es 3 horasIntervalo
            ps.setBoolean(4, false);//default es notificaciones activas, entonces detener=false
            ps.executeUpdate();
            con.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPreferencia(Notificacion notif) {
        try {
            ConexionBD con = new ConexionBD();
            String sql = "UPDATE notificaciones SET diasAntes=?, minutosIntervalo=?, detener=? WHERE usuario=?";
            PreparedStatement ps = con.crearPreparedStatement(sql);
            ps.setInt(1, notif.getDiasAntes());
            ps.setInt(2, notif.getMinutosIntervalo());
            ps.setBoolean(3, notif.isDetener());
            ps.setString(4, notif.getResponsableTarea());
            ps.executeUpdate();
            con.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Notificacion buscarPreferenciaPorUsuario(String usuario) {
        Notificacion notif = null;
        try {
            ConexionBD con = new ConexionBD();
            String sql = "SELECT * FROM notificaciones WHERE usuario=?";
            PreparedStatement ps = con.crearPreparedStatement(sql);
            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                notif = new Notificacion(rs.getInt("diasAntes"),
                        rs.getInt("minutosIntervalo"),
                        rs.getBoolean("detener"),
                        rs.getString("usuario"));
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notif;
    }

    //Eliminar la preferencia cuando se elimina el usuario
    public void eliminarPreferencia(String usuario) {
        try {
            ConexionBD con = new ConexionBD();
            String sql = "DELETE FROM notificaciones WHERE usuario=?";
            PreparedStatement ps = con.crearPreparedStatement(sql);
            ps.setString(1, usuario);
            ps.executeUpdate();
            con.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
