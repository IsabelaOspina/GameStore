package com.example.demo.Service;

import com.example.demo.Entity.Desarrolladora;
import com.example.demo.Repository.DesarrolladoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesarrolladoraService {
    private final DesarrolladoraRepository desarrolladoraRepository;

    public DesarrolladoraService(DesarrolladoraRepository desarrolladoraRepository) {
        this.desarrolladoraRepository = desarrolladoraRepository;
    }

    public Desarrolladora guardar(Desarrolladora desarrolladora) {
        return desarrolladoraRepository.save(desarrolladora);
    }

    public List<Desarrolladora> listar() {
        return desarrolladoraRepository.findAll();
    }
}
