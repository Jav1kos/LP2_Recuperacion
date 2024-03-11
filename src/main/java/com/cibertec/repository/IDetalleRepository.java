package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.detalle_venta;

public interface IDetalleRepository extends JpaRepository<detalle_venta, Integer> {

}
