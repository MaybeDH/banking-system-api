package com.uab.taller.store.usecase.rol;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllRolUseCase {
    @Autowired
    IRolService rolService;
    public List<Rol> getAllByRol(){
        return rolService.getAll();
    }
}
