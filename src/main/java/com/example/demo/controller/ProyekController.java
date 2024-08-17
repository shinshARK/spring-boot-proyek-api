package com.example.demo.controller;

import com.example.demo.dto.ProyekDTO;
import com.example.demo.model.Proyek;
import com.example.demo.service.ProyekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {
    private final ProyekService proyekService;

    @Autowired
    public ProyekController(ProyekService proyekService) {
        this.proyekService = proyekService;
    }

    @GetMapping
    public ResponseEntity<List<Proyek>> getProyeks() {
        return proyekService.getProyeks();
    }

    @PostMapping
    public ResponseEntity<?> addProyek(@RequestBody ProyekDTO proyekDTO) {

        return proyekService.addProyek(proyekDTO);
    }

    @PutMapping(path = "{proyekId}")
    public ResponseEntity<?> updateProyek(
            @PathVariable("proyekId") Integer proyekId,
            @RequestBody ProyekDTO updateRequest) {
        return proyekService.updateProyek(proyekId, updateRequest);
    }

    @DeleteMapping(path = "{proyekId}")
    public ResponseEntity<?> deleteProyek(@PathVariable("proyekId") Integer proyekId) {
        return proyekService.deleteProyek(proyekId);
    }
}
