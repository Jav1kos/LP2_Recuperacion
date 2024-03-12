package com.cibertec.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_venta")
public class venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre_comprador;
	@Temporal(TemporalType.DATE)
	private Date fecha_venta;
	private double monto_total;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre_comprador() {
		return nombre_comprador;
	}



	public void setNombre_comprador(String nombre_comprador) {
		this.nombre_comprador = nombre_comprador;
	}



	public Date getFecha_venta() {
		return fecha_venta;
	}



	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}



	public double getMonto_total() {
		return monto_total;
	}



	public void setMonto_total(double monto_total) {
		this.monto_total = monto_total;
	}



	@Override
	public String toString() {
		return "venta [id=" + id + 
				", nombre_comprador=" + nombre_comprador + 
				", fecha_venta=" + fecha_venta + 
				", monto_total=" + monto_total + "]";
	}
}
