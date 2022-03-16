package org.liceolapaz.des.jgv;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
					refrescarTabla();
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
				//Actualizar
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
