package com.uab.taller.store.controller;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.Transaction;
import com.uab.taller.store.domain.dto.request.RolRequest;
import com.uab.taller.store.service.IRolService;
import com.uab.taller.store.usecase.rol.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.event.IIOReadProgressListener;
import java.util.List;
@CrossOrigin

@RestController
@RequestMapping("Rol")
public class RolController {
    @Autowired
    IRolService rolService;
    @Autowired
    CreateRolUseCase createRolUseCase;
    @Autowired
    DeleteRolUseCase deleteRolUseCase;
    @Autowired
    UpdateRolUseCase updateRolUseCase;
    @Autowired
    GetAllRolUseCase getAllRolUseCase;
    @Autowired
    GetByIdRolUseCase getByIdRolUseCase;

    @GetMapping
    public List<Rol> getAllByRol(){
        return getAllRolUseCase.getAllByRol();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        deleteRolUseCase.deleteById(id);
    }
    @GetMapping("/{id}")
    public Rol getById(@PathVariable Long id){
        return getByIdRolUseCase.getById(id);
    }
    @PostMapping
    public Rol create(@RequestBody RolRequest rolRequest){
        return createRolUseCase.create(rolRequest);
    }
    @PutMapping("/{id}")
    public Rol update(@PathVariable Long id, @RequestBody RolRequest rolRequest){
        return updateRolUseCase.update(id, rolRequest);
    }
}
