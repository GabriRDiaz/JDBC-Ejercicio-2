package org.liceolapaz.des.jgv;

import java.util.Date;

public class Empleado {
	private String dni;
	private String nombre;
	private String ape1;
	private String ape2;
	private Date fechaNac;
	private double salario;
	private int depart;
	private String dniJefe;
	
	public Empleado() {}
	
	public Empleado(String dni, String nombre, String ape1, String ape2, Date fechaNac, double salario, int depart,
			String dniJefe) {
		this.dni = dni;
		this.nombre = nombre;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.fechaNac = fechaNac;
		this.salario = salario;
		this.depart = depart;
		this.dniJefe = dniJefe;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getDepart() {
		return depart;
	}

	public void setDepart(int depart) {
		this.depart = depart;
	}

	public String getDniJefe() {
		return dniJefe;
	}

	public void setDniJefe(String dniJefe) {
		this.dniJefe = dniJefe;
	}
	
}
