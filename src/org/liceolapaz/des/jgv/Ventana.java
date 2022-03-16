package org.liceolapaz.des.jgv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Ventana extends JFrame{
	private Dialogo dialogo;
	public Ventana(Dialogo dialogo, String usuario, String password, String baseDatos, String tabla) {
		this.dialogo=dialogo;
		this.setTitle((baseDatos+"-"+tabla).toUpperCase());
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		crearMenu();
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
