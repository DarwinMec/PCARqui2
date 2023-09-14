package com.example.visaalemania.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class PostulantDtos {

    private Long id;
    private String name;
    private Integer dni;
    private Integer age;
    private String status;
    private double salary;
    private boolean bag;
    private double calification;

    public PostulantDtos(String name, Integer dni, Integer age, String status, double salary, boolean bag, double calification) {
        this.name = name;
        this.dni = dni;
        this.age = age;
        this.status = status;
        this.salary = salary;
        this.bag = bag;
        this.calification = calification;
    }

    public void setCalification(Integer age, String status, boolean bag, double salary) {
    }
}



