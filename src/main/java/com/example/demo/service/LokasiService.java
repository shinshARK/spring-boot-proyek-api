package com.example.demo.service;


import com.example.demo.model.Lokasi;
import com.example.demo.repository.LokasiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokasiService {
    private final LokasiRepository lokasiRepository;

    @Autowired
    public LokasiService(LokasiRepository lokasiRepository) {
        this.lokasiRepository = lokasiRepository;
    }

    public ResponseEntity<List<Lokasi>> getLokasis() {
        List<Lokasi> lokasis = lokasiRepository.findAll();
        return ResponseEntity.ok(lokasis);
    }

    public ResponseEntity<Lokasi> addLokasi(Lokasi lokasi) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<Lokasi> updateLokasi(Integer lokasiId, String namaLokasi, String negara, String provinsi, String kota) {
        Lokasi lokasi = lokasiRepository.findById(lokasiId).orElse(null);
        if (lokasi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(namaLokasi != null && !namaLokasi.isEmpty() &&
                !namaLokasi.equals(lokasi.getNamaLokasi())
        ) {
            lokasi.setNamaLokasi(namaLokasi);
        }

        if(negara != null && !negara.isEmpty() &&
                !negara.equals(lokasi.getNegara())
        ) {
            lokasi.setNegara(negara);
        }

        if(provinsi != null && !provinsi.isEmpty() &&
                !provinsi.equals(lokasi.getProvinsi())
        ) {
            lokasi.setProvinsi(provinsi);
        }

        if(kota != null && !kota.isEmpty() &&
                !kota.equals(lokasi.getKota())
        ) {
            lokasi.setKota(kota);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Lokasi> deleteLokasi(Lokasi lokasi) {
        boolean exists = lokasiRepository.existsById(lokasi.getId());
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        lokasiRepository.delete(lokasi);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
