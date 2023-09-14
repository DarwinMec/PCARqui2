package com.example.visaalemania.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Postulant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameP;
    private Integer dni;
    private Integer ageP;
    private String status;
    private double salary;
    private boolean bag;
    
    private transient double calification;


}
