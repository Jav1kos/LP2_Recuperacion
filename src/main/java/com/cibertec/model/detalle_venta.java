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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_postal_destino", referencedColumnName ="codigo_postal")
	private ciudad codigo_postal_destino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_postal_origen", referencedColumnName ="codigo_postal")
	private ciudad codigo_postal_origen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venta", referencedColumnName ="id")
	private venta id_venta;
	
	private int cantidad;
	private Date fecha_viaje;
	private Date fecha_retorno;
	private double sub_total;
	
	public int getIdDetalle() {
		return id;
	}
	public void setIdDetalle(int idDetalle) {
		this.id = idDetalle;
	}
	
	public ciudad getCodigoDestino() {
		return codigo_postal_destino;
	}
	public void setCodigoDestino(ciudad codigo_destino) {
		this.codigo_postal_destino = codigo_destino;
	}
	
	public ciudad getCodigoOrigen() {
		return codigo_postal_origen;
	}
	public void setCodigoOrigen(ciudad codigo_origen) {
		this.codigo_postal_origen = codigo_origen;
	}
	
	public venta getIdVenta() {
		return id_venta;
	}
	public void setIdVenta(venta id_venta) {
		this.id_venta = id_venta;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Date getFechaViaje() {
		return fecha_viaje;
	}
	public void setFechaViaje(Date fecha_viaje) {
		this.fecha_viaje = fecha_viaje;
	}
	
	public Date getFechaRetorno(){
		return fecha_retorno;
	}
	public void setFechaRetorno(Date fecha_retorno) {
		this.fecha_retorno = fecha_retorno;
	}
	
	public double getSubTotal() {
		return sub_total;
	}
	public void setSubTotal(double sub_total) {
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
