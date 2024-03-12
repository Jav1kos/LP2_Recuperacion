package com.cibertec.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_detalle_venta")
public class detalle_venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "codigo_postal_origen", referencedColumnName ="codigo_postal")
	private ciudad codigo_postal_origen;
	
	@ManyToOne
	@JoinColumn(name = "codigo_postal_destino", referencedColumnName ="codigo_postal")
	private ciudad codigo_postal_destino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venta", referencedColumnName ="id")
	private venta id_venta;
	
	private int cantidad;
	private Date fecha_viaje;
	private Date fecha_retorno;
	private double sub_total;
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ciudad getCodigo_postal_origen() {
		return codigo_postal_origen;
	}



	public void setCodigo_postal_origen(ciudad codigo_postal_origen) {
		this.codigo_postal_origen = codigo_postal_origen;
	}



	public ciudad getCodigo_postal_destino() {
		return codigo_postal_destino;
	}



	public void setCodigo_postal_destino(ciudad codigo_postal_destino) {
		this.codigo_postal_destino = codigo_postal_destino;
	}



	public venta getId_venta() {
		return id_venta;
	}



	public void setId_venta(venta id_venta) {
		this.id_venta = id_venta;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public Date getFecha_viaje() {
		return fecha_viaje;
	}



	public void setFecha_viaje(Date fecha_viaje) {
		this.fecha_viaje = fecha_viaje;
	}



	public Date getFecha_retorno() {
		return fecha_retorno;
	}



	public void setFecha_retorno(Date fecha_retorno) {
		this.fecha_retorno = fecha_retorno;
	}



	public double getSub_total() {
		return sub_total;
	}



	public void setSub_total(double sub_total) {
		this.sub_total = sub_total;
	}



	@Override
	public String toString() {
		return "detalle_venta[id=" + id + 
				"codigo_postal_destino=" + codigo_postal_destino + 
				"codigo_postal_origen=" + codigo_postal_origen + 
				"id_venta=" + id_venta + 
				"cantidad=" + cantidad + 
				"fecha_viaje=" + fecha_viaje + 
				"fecha_retorno=" + fecha_retorno + 
				"sub_total=" + sub_total + "]";
	}
}
