package com.example.demo.Repository;

import com.example.demo.Entity.Genero;
import com.example.demo.Entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideojuegoRepository extends JpaRepository<Videojuego,Long> {

    List<Videojuego> findByGenero(Genero genero);

    List<Videojuego>findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT v FROM Videojuego v WHERE v.precio BETWEEN :min AND :max")
    List<Videojuego> buscarPorRangoDePrecio(
            @Param("min") Double precioMin,
            @Param("max") Double precioMax
    );

    @Query(
            value = "SELECT * FROM videojuegos ORDER BY fecha_creacion DESC LIMIT 5",
            nativeQuery = true
    )
    List<Videojuego> obtenerUltimos5();
}
