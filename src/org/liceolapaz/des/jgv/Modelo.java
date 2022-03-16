package org.liceolapaz.des.jgv;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class Modelo extends DefaultTableModel {
	public Modelo(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for (int i = 1; i <= cols; i++) {
				addColumn(rsmd.getColumnName(i));
			}
			
			while(rs.next()) {
				Object[] fila = new Object[cols];
				
				for (int i = 1; i <= cols; i++) {
					fila[i - 1] = rs.getObject(i);
				}
				
				addRow(fila);
			}
		} catch(SQLException e) {
			
		}
	}
	
	public void refrescarModelo(ResultSet rs) {
		try {
			setRowCount(0);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			
			while(rs.next()) {
				Object[] fila = new Object[cols];
				
				for (int i = 1; i <= cols; i++) {
					fila[i - 1] = rs.getObject(i);
				}
				
				addRow(fila);
			}
		} catch(SQLException e) {
			
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}
	
}
