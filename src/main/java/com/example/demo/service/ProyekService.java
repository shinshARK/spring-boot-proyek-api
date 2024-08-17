package com.example.demo.service;

import com.example.demo.dto.ProyekDTO;
import com.example.demo.model.Lokasi;
import com.example.demo.model.Proyek;
import com.example.demo.repository.LokasiRepository;
import com.example.demo.repository.ProyekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.util.StringUtils.isNullOrEmpty;

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

    public ResponseEntity<?> addProyek(ProyekDTO proyekDTO) {

        String namaProyek = proyekDTO.getNamaProyek();
        String client = proyekDTO.getClient();
        LocalDate tglMulai = proyekDTO.getTglMulai();
        LocalDate tglSelesai = proyekDTO.getTglSelesai();
        String pimpinanProyek = proyekDTO.getPimpinanProyek();
        String keterangan = proyekDTO.getKeterangan();

        List<Lokasi> lokasiList = lokasiRepository.findAllById(proyekDTO.getLokasiIds());

        if(isNullOrEmpty(namaProyek) ||
                isNullOrEmpty(client) ||
                isNullOrEmpty(pimpinanProyek) ||
                isNullOrEmpty(keterangan) ||
                tglMulai == null
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Lengkapi kolom yang dibutuhkan");
        }

        if(tglSelesai != null && tglMulai.isAfter(tglSelesai)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tanggal Mulai harus sebelum Tanggal Selesai");
        }

        // do i validate if theres a minimum of 1 location?
        // smth like
//        if(lokasiList.size() < 1) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Mohon isi minimal 1 lokasi");
//        }
        // but idk if this is in spec/needed

        Proyek proyek = new Proyek(
                namaProyek,
                client,
                tglMulai,
                tglSelesai,
                pimpinanProyek,
                keterangan,
                lokasiList
        );

        System.out.println(proyek.getId());
        proyekRepository.save(proyek);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created

    }

    public ResponseEntity<?> deleteProyek(Proyek proyek) {
        proyekRepository.delete(proyek);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }

    @Transactional
    public ResponseEntity<?> updateProyek(Integer proyekId, ProyekDTO updateRequest) {  // idk what to name this, DTO, xRequest or something aaa
        Optional<Proyek> proyekOptional = proyekRepository.findById(proyekId);


        if (!proyekOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proyek with id " + proyekId + " does not exist");
        }

        Proyek proyek = proyekOptional.get();

        LocalDate tglMulai = updateRequest.getTglMulai() != null ? updateRequest.getTglMulai() : proyek.getTglMulai();
        LocalDate tglSelesai = updateRequest.getTglSelesai() != null ? updateRequest.getTglSelesai() : proyek.getTglSelesai();

        // early return invalid date for tglSelesai
        if(tglSelesai != null && tglMulai.isAfter(tglSelesai)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tanggal Mulai harus sebelum Tanggal Selesai");
        }

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


