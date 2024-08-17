package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class ProyekDTO {
    private String namaProyek;
    private String client;
    private LocalDate tglMulai;
    private LocalDate tglSelesai;
    private String pimpinanProyek;
    private String keterangan;
    private List<Integer> lokasiIds;

    public ProyekDTO() {
    }

    public String getNamaProyek() {
        return namaProyek;
    }

    public void setNamaProyek(String namaProyek) {
        this.namaProyek = namaProyek;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDate getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(LocalDate tglMulai) {
        this.tglMulai = tglMulai;
    }

    public LocalDate getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(LocalDate tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getPimpinanProyek() {
        return pimpinanProyek;
    }

    public void setPimpinanProyek(String pimpinanProyek) {
        this.pimpinanProyek = pimpinanProyek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<Integer> getLokasiIds() {
        return lokasiIds;
    }

    public void setLokasiIds(List<Integer> lokasiIds) {
        this.lokasiIds = lokasiIds;
    }
}
