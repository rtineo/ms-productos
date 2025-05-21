package com.netec.app.service;

import java.util.List;
import java.util.Optional;

import com.netec.app.entities.Producto;

public interface IProductoService {
	public List<Producto> listarTodos() ;
	public Optional<Producto> obtenerPorId(Long id);
	public Producto guardar(Producto producto);
	public Producto actualizar(Long id, Producto productoActualizado);
	public void eliminar(Long id);
}