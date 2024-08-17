package com.example.demo.service;


import com.example.demo.model.Lokasi;
import com.example.demo.repository.LokasiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.StringUtils.isNullOrEmpty;
import static com.example.util.StringUtils.trimToNull;

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

    public ResponseEntity<?> addLokasi(Lokasi lokasi) {
        String namaLokasi = trimToNull(lokasi.getNamaLokasi());
        String negara = trimToNull(lokasi.getNegara());
        String provinsi = trimToNull(lokasi.getProvinsi());
        String kota = trimToNull(lokasi.getKota());

        if (isNullOrEmpty(namaLokasi) || isNullOrEmpty(negara) || isNullOrEmpty(provinsi) || isNullOrEmpty(kota)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Lengkapi kolom yang dibutuhkan");
        }

        lokasi.setNamaLokasi(namaLokasi);
        lokasi.setNegara(negara);
        lokasi.setProvinsi(provinsi);
        lokasi.setKota(kota);

        lokasiRepository.save(lokasi);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<Lokasi> updateLokasi(Integer lokasiId, Lokasi newLokasi) {
        Lokasi lokasi = lokasiRepository.findById(lokasiId).orElse(null);
        if (lokasi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String namaLokasi = newLokasi.getNamaLokasi();
        String negara = newLokasi.getNegara();
        String provinsi = newLokasi.getProvinsi();
        String kota = newLokasi.getKota();

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

    public ResponseEntity<Lokasi> deleteLokasi(Integer lokasiId) {
        boolean exists = lokasiRepository.existsById(lokasiId);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        lokasiRepository.deleteById(lokasiId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
