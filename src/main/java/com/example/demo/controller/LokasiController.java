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

    @PostMapping
    public ResponseEntity<Lokasi> addLokasi(@RequestBody Lokasi lokasi) {
        return lokasiService.addLokasi(lokasi);
    }

    @PutMapping(path = "{lokasiId}")
    public ResponseEntity<Lokasi> updateLokasi(
            @PathVariable Integer lokasiId,
            @RequestParam(required = false) String namaLokasi,
            @RequestParam(required = false) String negara,
            @RequestParam(required = false) String provinsi,
            @RequestParam(required = false) String kota) {
        return lokasiService.updateLokasi(lokasiId, namaLokasi, negara, provinsi, kota);
    }

    @DeleteMapping(path = "{lokasiId}")
    public ResponseEntity<Lokasi> deleteLokasi(@RequestBody Lokasi lokasi) {
        return lokasiService.deleteLokasi(lokasi);
    }
}
