package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "desarrolladoras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Desarrolladora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;

    private String sitioWeb;

    @OneToMany(mappedBy = "desarrolladora", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Videojuego> videojuegos;
}
