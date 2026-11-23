package com.bijlipay.service.implement;

import com.bijlipay.model.Vas;
import com.bijlipay.repository.VasRepository;
import com.bijlipay.service.VasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VasServiceImpl implements VasService {

    @Autowired
    private VasRepository vasRepository;

    @Override
    public Vas createVas(Vas vasRequest) {
        Vas vas = new Vas();
        vas.setName(vasRequest.getName());
        vas.setActive(true);
        vas.setVas(true);
        return vasRepository.save(vas);
    }

    @Override
    public Vas updateVas(Vas vasRequest) {
        Vas vas = vasRepository.findVasById(vasRequest.getId());
        vas.setActive(vasRequest.isActive());
        vas.setName(vasRequest.getName());
        vas.setVasCode(vasRequest.getVasCode());
        vas.setVas(vasRequest.isVas());
        return vasRepository.save(vasRequest);
    }

    @Override
    public Vas getVasById(int id) {
        return vasRepository.findVasById(id);
    }

    @Override
    public List<Vas> getAllVas() {
        return vasRepository.getAll();
    }


}
