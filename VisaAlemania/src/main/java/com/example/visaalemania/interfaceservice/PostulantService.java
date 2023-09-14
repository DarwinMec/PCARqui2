package com.example.visaalemania.interfaceservice;

import com.example.visaalemania.dtos.PostulantDtos;
import com.example.visaalemania.entities.Postulant;
import com.example.visaalemania.repository.PostulantRepository;
import org.hibernate.mapping.Bag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostulantService {
   @Autowired
   private PostulantRepository postulantRepository;
   public Postulant register(Postulant postulant){
      return postulantRepository.save(postulant);
   }
   public List<Postulant> listPostulant(){
      return postulantRepository.findAll();
   }
   public double calcularCalificacion(int ageP,String status, boolean bag, double salary) {

      double factorEstadoCivil = (status.equals("soltero") || status.equals("divorciado") || status.equals("viudo")) ? 0.5 : 1;
      double factorBolsaViaje = bag ? 20 : 0;

      return (ageP * factorEstadoCivil) + (salary / 1000.00) + factorBolsaViaje;
   }
   public boolean isApproved(int ageP,String status, boolean bag, double salary){
      return calcularCalificacion(ageP, status,bag,salary) > 60;
   }
   public List<PostulantDtos> listPostulantCalification(){
      List<Postulant> list=postulantRepository.findAll();
      List<PostulantDtos>listDTO=convertToLisDto(list);
      for (PostulantDtos p:listDTO){
         p.setCalification(calcularCalificacion(p.getAge(), p.getStatus(), p.isBag(), p.getSalary()));
      }
      return listDTO;
   }



   private List<PostulantDtos> convertToLisDto(List<Postulant> list){
      return list.stream()
              .map(this::convertToDto)
              .collect(Collectors.toList());
   }
   private PostulantDtos convertToDto(Postulant author) {
      ModelMapper modelMapper = new ModelMapper();
      return modelMapper.map(author, PostulantDtos.class);
   }
}
