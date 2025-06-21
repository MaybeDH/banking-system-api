package com.uab.taller.store.usecase.rol;

import com.uab.taller.store.domain.Rol;
import com.uab.taller.store.domain.dto.request.RolRequest;
import com.uab.taller.store.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UpdateRolUseCase {
    @Autowired
    IRolService rolService;
    public Rol update(@PathVariable Long id, @RequestBody RolRequest rolRequest){
        Rol rol = rolService.getById(id);
        rol.setName(rolRequest.getName());
        rol.setDescription(rolRequest.getDescription());
        return rolService.save(rol);
    }
}
