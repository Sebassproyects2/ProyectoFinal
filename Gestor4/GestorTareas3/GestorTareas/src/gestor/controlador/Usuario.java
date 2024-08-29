package gestor.controlador;

import gestor.modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private String password;
    private String rol;

    public Usuario(String nombre, String email, String password, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {
    }

    public boolean regisUser() {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement consulta = conexionBD.getConexion().prepareStatement(sql);
            consulta.setString(1, nombre);
            consulta.setString(2, email);
            consulta.setString(3, password);
            consulta.setString(4, rol);
            consulta.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            conexionBD.cerrarConexion();
        }
    }

    public static boolean authUser(String email, String password) {
        ConexionBD conexionBD = new ConexionBD();
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";
        try {
            PreparedStatement consulta = conexionBD.getConexion().prepareStatement(sql);
            consulta.setString(1, email);
            consulta.setString(2, password);
            ResultSet resultado = consulta.executeQuery();
            return resultado.next();  // true si encuentra el usuario
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            conexionBD.cerrarConexion();
        }
    }

    public ArrayList<String> todosUsuario() {
        ArrayList<String> miListaUsuario = new ArrayList<>();
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            Statement st = con.crearStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                String usuario = rs.getString("nombre") + " ("
                        + rs.getString("email") + ")";
                miListaUsuario.add(usuario);
            }
            rs.close();
            con.cerrarConexion();
        } catch (SQLException e) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);
        }
        return miListaUsuario;
    }

    public Usuario BuscarUsuarioEmail(String email) {
        Usuario usuario = null;
        try {
            //1-crear la conexion con la bd
            ConexionBD con = new ConexionBD();
            //creamos el statement
            PreparedStatement st = con.crearPreparedStatement("SELECT * FROM usuarios WHERE email=?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getString("nombre"),
                        rs.getString("email"), rs.getString("password"), rs.getString("rol"));
            }
            rs.close();
            con.cerrarConexion();

        } catch (SQLException e) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);
        }
        return usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }
}
