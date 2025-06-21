package com.uab.taller.store.service;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImp implements IRolService{
    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> getAll(){
        return rolRepository.findAll();
    }
    @Override
    public Rol save(Rol rol){
        return rolRepository.save(rol);
    }
    @Override
    public Rol getById(Long id){
        return rolRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public void deleteById(Long id){
        rolRepository.deleteById(id);
    }
}
