package com.example.demo.Service;

import com.example.demo.Entity.Desarrolladora;
import com.example.demo.Entity.Genero;
import com.example.demo.Entity.Videojuego;
import com.example.demo.Repository.DesarrolladoraRepository;
import com.example.demo.Repository.VideojuegoRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;
    private final DesarrolladoraRepository desarrolladoraRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository,
                             DesarrolladoraRepository desarrolladoraRepository) {
        this.videojuegoRepository = videojuegoRepository;
        this.desarrolladoraRepository = desarrolladoraRepository;
    }

    public Videojuego guardar(Videojuego videojuego) {

        validarVideojuego(videojuego);

        Long desarrolladoraId = videojuego.getDesarrolladora().getId();

        Desarrolladora desarrolladora = desarrolladoraRepository.findById(desarrolladoraId)
                .orElseThrow(() -> new ResourceNotFoundException("La desarrolladora no existe"));

        videojuego.setDesarrolladora(desarrolladora);

        return videojuegoRepository.save(videojuego);
    }

    public List<Videojuego> listar() {
        List<Videojuego> lista = videojuegoRepository.findAll();
        lista.forEach(Videojuego::getPrecioConIva);
        return lista;
    }

    public Videojuego obtenerPorId(Long id) {
        return videojuegoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Videojuego no encontrado con id: " + id));
    }

    public Videojuego actualizar(Long id, Videojuego videojuego) {

        Videojuego existente = obtenerPorId(id);

        validarVideojuego(videojuego);

        Long desarrolladoraId = videojuego.getDesarrolladora().getId();

        Desarrolladora desarrolladora = desarrolladoraRepository.findById(desarrolladoraId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("La desarrolladora no existe"));

        existente.setTitulo(videojuego.getTitulo());
        existente.setPrecio(videojuego.getPrecio());
        existente.setCodigoRegistro(videojuego.getCodigoRegistro());
        existente.setGenero(videojuego.getGenero());
        existente.setDesarrolladora(desarrolladora);

        return videojuegoRepository.save(existente);
    }

    public void eliminar(Long id) {

        if (!videojuegoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Videojuego no encontrado con id: " + id);
        }

        videojuegoRepository.deleteById(id);
    }

    public Videojuego aplicarDescuento(Long id, double porcentaje) {

        Videojuego videojuego = obtenerPorId(id);

        if (porcentaje <= 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 1 y 100");
        }

        double descuento = videojuego.getPrecio() * (porcentaje / 100);
        double nuevoPrecio = videojuego.getPrecio() - descuento;

        videojuego.setPrecio(nuevoPrecio);

        return videojuegoRepository.save(videojuego);
    }

    public List<Videojuego> buscarPorTitulo(String titulo) {
        return videojuegoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Videojuego> buscarPorRango(Double min, Double max) {
        return videojuegoRepository.buscarPorRangoDePrecio(min, max);
    }


    private void validarVideojuego(Videojuego videojuego) {

        if (videojuego.getPrecio() == null || videojuego.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        if (videojuego.getTitulo() == null || videojuego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar vacío");
        }

        if (videojuego.getDesarrolladora() == null ||
                videojuego.getDesarrolladora().getId() == null) {
            throw new IllegalArgumentException("Debe especificar una desarrolladora válida");
        }
    }
}
