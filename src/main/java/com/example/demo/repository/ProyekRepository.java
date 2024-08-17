package com.example.demo.repository;

import com.example.demo.model.Proyek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Integer> {

}
