package com.uab.taller.store.usecase.rol;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class GetByIdRolUseCase {
    @Autowired
    IRolService rolService;
    public Rol getById(@PathVariable Long id){
        return rolService.getById(id);
    }
}
