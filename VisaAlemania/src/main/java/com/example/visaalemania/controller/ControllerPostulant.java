package com.example.visaalemania.controller;

import com.example.visaalemania.dtos.PostulantDtos;
import com.example.visaalemania.entities.Postulant;
import com.example.visaalemania.interfaceservice.PostulantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ControllerPostulant {
    @Autowired
    private PostulantService postulantService;
    //A. POST para registrar postulantes
    @PostMapping("/postulant")
        public ResponseEntity<PostulantDtos> register(@RequestBody PostulantDtos postulantDtos){
        Postulant postulant = convertToEntity(postulantDtos);
        postulant = postulantService.register(postulant);
        postulantDtos = convertToDto(postulant);
        return new ResponseEntity<PostulantDtos>(postulantDtos, HttpStatus.OK);
    }
    //E. GET para listar todos los postulantes cada uno con sus calificaciones
    @GetMapping("/postulants")
    public ResponseEntity<List<PostulantDtos>> listPostulants() throws Exception {
        List<PostulantDtos> postulant = null;
        try {
            postulant = postulantService.listPostulantCalification();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("No es posible obtenerlo");
        }
        return new ResponseEntity<List<PostulantDtos>>(postulant, HttpStatus.OK);
    }


    private PostulantDtos convertToDto(Postulant author) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(author, PostulantDtos.class);
    }

    private Postulant convertToEntity(PostulantDtos authorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(authorDTO, Postulant.class);
    }

    private List<PostulantDtos> convertToLisDto(List<Postulant> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
