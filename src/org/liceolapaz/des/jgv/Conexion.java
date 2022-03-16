package org.liceolapaz.des.jgv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private Connection conexion;
	private String tabla;
	
	public Conexion(String baseDatos, String tabla, String usuario, String contrasena) throws SQLException {
		String conexionUrl = "jdbc:mysql://localhost/"+baseDatos+"?serverTimezone=Europe/Madrid";
		this.conexion = DriverManager.getConnection(conexionUrl, usuario, contrasena);
		this.tabla=tabla;
	}
	
	public Connection getConexion() {
		return this.conexion;
	}
	
	public String getTabla() {
		return this.tabla;
	}
}
