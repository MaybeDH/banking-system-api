package com.uab.taller.store.usecase.rol;

import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class DeleteRolUseCase {
    @Autowired
    IRolService rolService;
    public void deleteById(@PathVariable Long id){
        rolService.deleteById(id);
    }
}
