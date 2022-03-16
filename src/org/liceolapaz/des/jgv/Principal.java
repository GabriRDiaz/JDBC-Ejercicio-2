package org.liceolapaz.des.jgv;

import java.sql.SQLException;

public class Principal {
	public static void main(String[] args) throws SQLException {
			//Conexion conn = new Conexion("prog","empleados","db","1234");
			Dialogo dial = new Dialogo();
			dial.setVisible(true);
		}
}
