package com.netec.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.netec.app.entities.Producto;
import com.netec.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	private final ProductoRepository productoRepository;

	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> listarTodos() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerPorId(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public Producto guardar(Producto producto) {
		if (productoRepository.existsByNombre(producto.getNombre())) {
			throw new IllegalArgumentException("El producto ya existe con ese nombre.");
		}
		return productoRepository.save(producto);
	}

	@Override
	public Producto actualizar(Long id, Producto productoActualizado) {
		return productoRepository.findById(id).map(producto -> {
			producto.setNombre(productoActualizado.getNombre());
			producto.setDescripcion(productoActualizado.getDescripcion());
			producto.setPrecio(productoActualizado.getPrecio());
			producto.setStock(productoActualizado.getStock());
			return productoRepository.save(producto);
		}).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	}

	@Override
	public void eliminar(Long id) {
		productoRepository.deleteById(id);
	}
}
