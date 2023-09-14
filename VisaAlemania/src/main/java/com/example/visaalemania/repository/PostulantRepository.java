package com.example.visaalemania.repository;

import com.example.visaalemania.entities.Postulant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulantRepository extends JpaRepository<Postulant, Long> {

}
