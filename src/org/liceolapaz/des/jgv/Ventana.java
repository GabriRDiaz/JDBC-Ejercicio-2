package org.liceolapaz.des.jgv;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame{
	private Dialogo dialogo;
	private Connection conexion;
	private JTable tabla;
	private Modelo modelo;
	private JScrollPane panelTabla;
	public Ventana(Dialogo dialogo, String usuario, String password, String baseDatos, String tabla) throws SQLException {
		
		this.dialogo=dialogo;
		this.conexion= new Conexion(baseDatos, tabla, usuario, password).getConexion();
		
		this.setTitle((baseDatos+"-"+tabla).toUpperCase());
		
		setLayout(new BorderLayout());
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		crearMenu();
		crearTabla();
		crearBotones();
		
	}
	
	private void insertarFila() {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.addRow(new Object[]{});
	}
	
	private void eliminarFila() {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		int[] filas = tabla.getSelectedRows();
		
		for (int fila : filas) {
			modelo.removeRow(tabla.convertRowIndexToModel(fila));
		}
		
        tabla.clearSelection();
		
	}
	
	private void refrescarTabla() throws SQLException {
		PreparedStatement ps = conexion.prepareStatement("SELECT * FROM empleados");
		ResultSet rs = ps.executeQuery();
		
		modelo.refrescarModelo(rs);
		modelo.fireTableDataChanged();
		tabla.repaint();
	}
	
	private void guardarDatos() throws SQLException {
		List<Empleado> empleados = new ArrayList<Empleado>();
		for (int i = 0; modelo.getRowCount() > i; i++) {
			Empleado empleado = new Empleado();
			if(modelo.getValueAt(i, 0).toString()==null) {
				empleado.setDni("-");
			}else {
				empleado.setDni(modelo.getValueAt(i, 0).toString());
			}
			
			if(modelo.getValueAt(i, 1).toString()==null) {
				empleado.setNombre("-");
			}else {
				empleado.setNombre(modelo.getValueAt(i, 1).toString());
			}
            
			if(modelo.getValueAt(i, 2).toString()==null) {
				empleado.setApe1("-");
			}else {
				empleado.setApe1(modelo.getValueAt(i, 2).toString());
			}
			
			if(modelo.getValueAt(i, 3).toString()==null) {
				empleado.setApe2("-");
			}else {
				empleado.setApe2(modelo.getValueAt(i, 3).toString());
			}
			
			if(modelo.getValueAt(i, 4).toString()==null) {
				empleado.setFechaNac(Date.valueOf("0000-00-00"));
			}else {
				empleado.setFechaNac(Date.valueOf(modelo.getValueAt(i, 4).toString()));
			}
            
			if(modelo.getValueAt(i, 5).toString()==null) {
				empleado.setSalario(0.0);
			}else {
				empleado.setSalario(Double.parseDouble(modelo.getValueAt(i, 5).toString()));
			}
            
			if(modelo.getValueAt(i, 6).toString()==null) {
				empleado.setDepart(-1);
			}else {
				empleado.setDepart(Integer.parseInt(modelo.getValueAt(i, 6).toString()));
			}
			
			if(modelo.getValueAt(i, 7).toString()==null) {
				empleado.setDniJefe("-");
			}else {
				empleado.setDniJefe(modelo.getValueAt(i, 7).toString());
			}
			
            empleados.add(empleado);
		}
        empleados.forEach(e->{
        	System.out.println(e.getNombre());
        });
	    String limpiarTabla = "TRUNCATE TABLE empleados;";
	    PreparedStatement ps = conexion.prepareStatement(limpiarTabla);
	    ps.executeUpdate();
	    String cargarDatos = "INSERT INTO empleados(dni,nombre,prim_ape,seg_ape,fec_nac,salario,depart,dni_jefe) "
	    		+ "VALUES ('";
        empleados.forEach(e->{
        	try {
				PreparedStatement pst =conexion.prepareStatement(cargarDatos+
						e.getDni()+"','"+e.getNombre()+"','"+e.getApe1()+"','"+
						e.getApe2()+"','"+e.getFechaNac()+"','"+e.getSalario()+"','"+
						e.getDepart()+"','"+e.getDniJefe()+"');");
				pst.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        });
        refrescarTabla();
	}
	
	private void crearBotones() {
		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(1,4,30,30));
		JButton bInsertar = new JButton("Insertar Fila");
		bInsertar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarFila();
			}

		});

		botones.add(bInsertar);
		
		JButton bEliminar = new JButton("Eliminar Fila");
		bEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarFila();
			}
		});

		botones.add(bEliminar);
		
		JButton bRefrescar = new JButton("Refrescar Tabla");
		bRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(null, "Se perderán los datos no guardados. ¿Continuar?", "Aviso",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						refrescarTabla();
					} else {
					   return;
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		botones.add(bRefrescar);
		
		JButton bActualizar = new JButton("Actualizar BD");
		bActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(null, "Se guardarán los datos. ¿Continuar?", "Aviso",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						guardarDatos();
					} else {
					   return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//bActualizar.setSize(50,20);
		botones.add(bActualizar);
		
		this.add(botones,BorderLayout.PAGE_END);
	}
	
	private void crearTabla() throws SQLException {
		PreparedStatement ps = conexion.prepareStatement("SELECT * FROM empleados");
		ResultSet rs = ps.executeQuery();
		modelo = new Modelo(rs);

		tabla = new JTable(modelo);
		tabla.setFillsViewportHeight(true);

		panelTabla = new JScrollPane(tabla);
		this.add(panelTabla, BorderLayout.CENTER);
		
	}
	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuOpciones = new JMenu("Opciones");
		menuOpciones.setMnemonic(KeyEvent.VK_O);
		JMenuItem nuevaConsulta = new JMenuItem("Nueva Consulta");
		nuevaConsulta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevaConsulta();
			}
		});
		
		menuBar.add(menuOpciones);
		menuOpciones.add(nuevaConsulta);
		
		setJMenuBar(menuBar);
	}
	
	private void nuevaConsulta() {
		this.setVisible(false);
		dialogo.setVisible(true);
	}
}
