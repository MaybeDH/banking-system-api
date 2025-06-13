package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.RolRequest;
import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.event.IIOReadProgressListener;
import java.util.List;

@RestController
@RequestMapping("Rol")
public class RolController {
    @Autowired
    IRolService rolService;

    @GetMapping
    public List<Rol> getAllByRol(){
        return rolService.getAll();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        rolService.deleteById(id);
    }
    @GetMapping("/{id}")
    public Rol getById(@PathVariable Long id){
        return rolService.getById(id);
    }
    @PostMapping
    public Rol create(@RequestBody RolRequest rolRequest){
        Rol rol = new Rol();
        rol.setName(rolRequest.getName());
        rol.setDescription(rolRequest.getDescription());
        return rolService.save(rol);
    }
    @PutMapping("/{id}")
    public Rol update(@PathVariable Long id, @RequestBody RolRequest rolRequest){
        Rol rol = rolService.getById(id);
        rol.setName(rolRequest.getName());
        rol.setDescription(rolRequest.getDescription());
        return rolService.save(rol);
    }
}
