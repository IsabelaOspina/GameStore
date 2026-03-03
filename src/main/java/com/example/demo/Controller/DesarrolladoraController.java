package com.example.demo.Controller;

import com.example.demo.Entity.Desarrolladora;
import com.example.demo.Service.DesarrolladoraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desarrolladoras")
public class DesarrolladoraController {

    private final DesarrolladoraService desarrolladoraService;

    public DesarrolladoraController(DesarrolladoraService desarrolladoraService) {
        this.desarrolladoraService = desarrolladoraService;
    }

    @GetMapping
    public List<Desarrolladora> listar() {
        return desarrolladoraService.listar();
    }

    @PostMapping
    public Desarrolladora crear(@RequestBody Desarrolladora desarrolladora) {
        return desarrolladoraService.guardar(desarrolladora);
    }
}
