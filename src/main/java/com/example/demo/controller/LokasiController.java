package com.example.demo.controller;


import com.example.demo.model.Lokasi;
import com.example.demo.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lokasi")
public class LokasiController {
    private final LokasiService lokasiService;

    @Autowired
    public LokasiController(LokasiService lokasiService) {
        this.lokasiService = lokasiService;
    }

    @GetMapping
    public ResponseEntity<List<Lokasi>> getLokasis() {
        return lokasiService.getLokasis();
    }

    @GetMapping(path = "{lokasiId}")
    public ResponseEntity<Lokasi> getLokasi(@PathVariable Integer lokasiId) {
        return lokasiService.getLokasi(lokasiId);
    }

    @PostMapping
    public ResponseEntity<?> addLokasi(@RequestBody Lokasi lokasi) {
        return lokasiService.addLokasi(lokasi);
    }

    @PutMapping(path = "{lokasiId}")
    public ResponseEntity<Lokasi> updateLokasi(
            @PathVariable Integer lokasiId,
            @RequestBody Lokasi lokasi) {

        return lokasiService.updateLokasi(lokasiId, lokasi);
    }

    @DeleteMapping(path = "{lokasiId}")
    public ResponseEntity<Lokasi> deleteLokasi(@PathVariable Integer lokasiId) {
        return lokasiService.deleteLokasi(lokasiId);
    }
}
