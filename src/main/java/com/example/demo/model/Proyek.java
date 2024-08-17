package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Proyek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_proyek", nullable = false)
    private String namaProyek;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "tgl_mulai", nullable = false)
    private LocalDate tglMulai;

    @Column(name = "tgl_selesai", nullable = true)
    private LocalDate tglSelesai;

    @Column(name = "pimpinan_proyek", nullable = false)
    private String pimpinanProyek;

    @Column(name = "keterangan", nullable = true)
    private String keterangan;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt; // should i even have this?

    @ManyToMany
    @JoinTable(
            name = "proyek_lokasi",
            joinColumns = @JoinColumn(name = "proyek_id"),
            inverseJoinColumns = @JoinColumn(name = "lokasi_id")
    )
    private List<Lokasi> lokasis;

    // Constructors
    public Proyek() {
    }

    public Proyek(String namaProyek, String client, LocalDate tglMulai, LocalDate tglSelesai, String pimpinanProyek, String keterangan) {
        this.namaProyek = namaProyek;
        this.client = client;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.pimpinanProyek = pimpinanProyek;
        this.keterangan = keterangan;
    }

    public Proyek(String namaProyek, String client, LocalDate tglMulai, LocalDate tglSelesai, String pimpinanProyek, String keterangan, LocalDateTime createdAt) {
        this.namaProyek = namaProyek;
        this.client = client;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.pimpinanProyek = pimpinanProyek;
        this.keterangan = keterangan;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Lokasi> getLokasis() {
        return lokasis;
    }

    public void setLokasis(List<Lokasi> lokasis) {
        this.lokasis = lokasis;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
