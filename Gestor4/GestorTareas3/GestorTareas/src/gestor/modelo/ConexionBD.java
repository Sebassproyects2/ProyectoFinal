package gestor.modelo;

import java.sql.*;

/**
 *
 * @author Angel
 */
public class ConexionBD {

    private Connection conexion = null;
    private PreparedStatement consulta = null;
    private ResultSet resultado = null;
    private String servidor = "localhost:3307";
    private String database = "bdgestor";
    private String usuario = "root";
    private String contrasenia = "";
    private String url = "jdbc:mysql://" + servidor + "/" + database;

    public ConexionBD() {
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasenia);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (resultado != null) resultado.close();
            if (consulta != null) consulta.close();
            if (conexion != null) conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public PreparedStatement crearPreparedStatement(String sql) throws SQLException {
        return conexion.prepareStatement(sql);
    }

    public Statement crearStatement() throws SQLException {
        return conexion.createStatement();
    }
}