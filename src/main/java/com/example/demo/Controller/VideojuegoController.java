package com.example.demo.Controller;

import com.example.demo.Entity.Videojuego;
import com.example.demo.Service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public List<Videojuego> listar() {
        return  videojuegoService.listar();
    }

    @GetMapping("/{id}")
    public Videojuego obtenerPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id);
    }

    @PostMapping
    public Videojuego crear(@RequestBody Videojuego videojuego) {
        return videojuegoService.guardar(videojuego);
    }

    @PutMapping("/{id}")
    public Videojuego actualizar(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        return videojuegoService.actualizar(id, videojuego);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        videojuegoService.eliminar(id);
    }

    @GetMapping("/buscar")
    public List<Videojuego> buscarPorTitulo(@RequestParam String titulo) {
        return videojuegoService.buscarPorTitulo(titulo);
    }

    @GetMapping("/rango-precio")
    public List<Videojuego> buscarPorRango(
            @RequestParam Double min,
            @RequestParam Double max) {

        return videojuegoService.buscarPorRango(min, max);
    }

    @PatchMapping("/{id}/descuento")
    public Videojuego aplicarDescuento(
            @PathVariable Long id,
            @RequestParam double porcentaje) {

        return videojuegoService.aplicarDescuento(id, porcentaje);
    }
}

