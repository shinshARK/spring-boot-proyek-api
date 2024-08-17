package com.example.demo.service;

import com.example.demo.dto.ProyekUpdateRequest;
import com.example.demo.model.Lokasi;
import com.example.demo.model.Proyek;
import com.example.demo.repository.LokasiRepository;
import com.example.demo.repository.ProyekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProyekService {
    private final ProyekRepository proyekRepository;
    private final LokasiRepository lokasiRepository;

    @Autowired
    public ProyekService(ProyekRepository proyekRepository, LokasiRepository lokasiRepository) {
        this.proyekRepository = proyekRepository;
        this.lokasiRepository = lokasiRepository;
    }

    public ResponseEntity<List<Proyek>> getProyeks() {
        List<Proyek> proyeks = proyekRepository.findAll();
        return ResponseEntity.ok(proyeks); // 200 OK
    }

    public ResponseEntity<?> addProyek(Proyek proyek) {
        proyekRepository.save(proyek);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created

    }

    public ResponseEntity<?> deleteProyek(Proyek proyek) {
        proyekRepository.delete(proyek);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }

    @Transactional
    public ResponseEntity<?> updateProyek(Integer proyekId, ProyekUpdateRequest updateRequest) {
        Optional<Proyek> proyekOptional = proyekRepository.findById(proyekId);

        if (!proyekOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proyek with id " + proyekId + " does not exist");
        }

        Proyek proyek = proyekOptional.get();
        // Update only fields that are present in the update request
        if (updateRequest.getNamaProyek() != null) {
            proyek.setNamaProyek(updateRequest.getNamaProyek());
        }
        if (updateRequest.getClient() != null) {
            proyek.setClient(updateRequest.getClient());
        }
        if (updateRequest.getTglMulai() != null) {
            proyek.setTglMulai(updateRequest.getTglMulai());
        }
        if (updateRequest.getTglSelesai() != null) {
            proyek.setTglSelesai(updateRequest.getTglSelesai());
        }
        if (updateRequest.getPimpinanProyek() != null) {
            proyek.setPimpinanProyek(updateRequest.getPimpinanProyek());
        }
        if (updateRequest.getKeterangan() != null) {
            proyek.setKeterangan(updateRequest.getKeterangan());
        }

        // Update associated locations
        List<Lokasi> lokasiList = lokasiRepository.findAllById(updateRequest.getLokasiIds());
        proyek.setLokasis(lokasiList);

//        proyekRepository.save(proyek); // do i need this?

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<?> deleteProyek(Integer proyekId) {
        boolean exists = proyekRepository.existsById(proyekId);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proyek with id " + proyekId + " does not exist");
        }
        proyekRepository.deleteById(proyekId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
}
