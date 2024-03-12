package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.model.ciudad;

@Repository
public interface ICiudadRepository extends JpaRepository<ciudad, String> {

}
