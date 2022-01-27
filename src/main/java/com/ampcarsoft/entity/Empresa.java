package com.ampcarsoft.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 
 * @author Angel Mu�oz Pe�a Universidad VIU
 *
 */
@Entity
@Table(name = "empresas")
public class Empresa {
	@NotEmpty
	@Size(min = 9)
	@Id
	private String cif;
	@NotEmpty
	@Size(min = 2)
	private String nombre;
	@NotEmpty
	@Size(min = 8)
	private String direccion;
	private String logo;

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
