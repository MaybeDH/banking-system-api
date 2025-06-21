package com.uab.taller.store.usecase.rol;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.dto.request.RolRequest;
import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CreateRolUseCase {
    @Autowired
    IRolService rolService;
    public Rol create(@RequestBody RolRequest rolRequest){
        Rol rol = new Rol();
        rol.setName(rolRequest.getName());
        rol.setDescription(rolRequest.getDescription());
        return rolService.save(rol);
    }
}
