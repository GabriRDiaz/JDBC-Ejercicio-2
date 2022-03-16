package org.liceolapaz.des.jgv;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Dialogo extends JDialog {
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JTextField txtBaseDatos;
	private JTextField txtTabla;
	
	public Dialogo() {
		super();
		setTitle("Escoja la base de datos");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 10, 10));
		
		JLabel lbUsuario = new JLabel("Usuario");
		panel.add(lbUsuario);
		
		txtUsuario = new JTextField("root");
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		
		panel.add(txtUsuario);
		
		JLabel lbPassword = new JLabel("Contrase�a");
		panel.add(lbPassword);
		txtPassword = new JPasswordField("1234");
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		panel.add(txtPassword);
		
		JLabel lbBaseDatos = new JLabel("Base datos");
		panel.add(lbBaseDatos);
		txtBaseDatos = new JTextField("prog");
		txtBaseDatos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		panel.add(txtBaseDatos);
		
		JLabel lbTabla = new JLabel("Tabla");
		panel.add(lbTabla);
		txtTabla = new JTextField("empleados");
		txtTabla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					aceptar();
				}
			}
		});
		panel.add(txtTabla);
		
		
		JButton bAceptar = new JButton("Aceptar");
		bAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptar();				
			}
		});
		panel.add(bAceptar);
		JButton bCancelar = new JButton("Cancelar");
		bCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		panel.add(bCancelar);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel, BorderLayout.CENTER);
	}
	
	private void cancelar() {
		/*if (ventana == null) {
			System.exit(0);
		}
		ventana.setVisible(true);
		setVisible(false);*/
		System.exit(0);
	}
	
	private void aceptar() {
		String usuario;
		String password;
		String baseDatos;
		String tabla;
		
		usuario = txtUsuario.getText();
		password = txtPassword.getText();
		baseDatos = txtBaseDatos.getText();
		tabla = txtTabla.getText();
		

		if (usuario.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca la contrase�a", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (baseDatos.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (tabla.equals("")) {
			JOptionPane.showMessageDialog(this, "Introduzca la tabla", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(baseDatos.equals("prog") && tabla.equals("empleados")) {
			System.out.println("ok");
		}else {
			JOptionPane.showMessageDialog(this, "La base de datos o la tabla no son correctas", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Ventana ventana = new Ventana(this, usuario, password, baseDatos, tabla);
		ventana.setVisible(true);
		this.setVisible(false);
	}
}
