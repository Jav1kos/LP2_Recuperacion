package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.ciudad;

public interface ICiudadRepository extends JpaRepository<ciudad, String> {

}
