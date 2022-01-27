package com.ampcarsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;

@Entity
@Table(name = "coches")
public class Coche implements Serializable {

	@NotEmpty
	@Size(min = 4)
	@Id
	@Column(name = "matricula")
	private String matricula;
	@NotEmpty
	@Size(min = 2)
	private String marca;
	@NotEmpty
	private String modelo;
	@Min(value = 50)
	private int potencia;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cliente_id", referencedColumnName = "id", insertable = true, updatable = true)    
	private User cliente = new User();

	@Transient
	private int numPuertas;

	public int getNumPuertas() {
		return numPuertas;
	}

	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	public Coche() {
		super();
	}

	public Coche(String matricula, String marca, String modelo, int potencia) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.potencia = potencia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", potencia=" + potencia
				+ ", numPuertas=" + numPuertas + "]";
	}

	public User getCliente() {
		return cliente;
	}

	public void setCliente(User cliente) {
		this.cliente = cliente;
	}

}
