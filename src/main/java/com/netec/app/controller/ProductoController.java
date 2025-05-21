  package com.netec.app.controller;

  import java.util.List;
  import java.util.Map;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.core.env.Environment;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.DeleteMapping;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.PutMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;

  import com.netec.app.entities.Producto;
  import com.netec.app.service.IProductoService;

  @RestController
  @RequestMapping("/productos")
  public class ProductoController {

      private final IProductoService productoService;

      @Autowired
      private Environment environment;

      public ProductoController(IProductoService productoService) {
          this.productoService = productoService;
      }

      /*
  POD_NAME: Obtiene el nombre del Pod desde la metadata.
  POD_ID: Obtiene la IP del Pod desde el estado.
 */

  @GetMapping
  public Map<String, Object> listarTodos() {
      return Map.of(
          "POD_NAME", environment.getProperty("POD_NAME", "Unknown"),   
          "POD_ID", environment.getProperty("POD_ID", "Unkown"), 
          "SALUDO", environment.getProperty("config.saludo", "Unknown"),
          "productos", productoService.listarTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
      return productoService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
      return ResponseEntity.ok(productoService.guardar(producto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
      return ResponseEntity.ok(productoService.actualizar(id, producto));
  }

  @DeleteMapping("/{id}")
      public ResponseEntity<Void> eliminar(@PathVariable Long id) {
          productoService.eliminar(id);
          return ResponseEntity.noContent().build();
      }
  }