package com.netec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netec.app.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombre(String nombre);
}