package com.bijlipay.service;

import com.bijlipay.model.Vas;
import java.util.List;

public interface VasService {

    Vas createVas(Vas vas);

    Vas updateVas(Vas vasRequest);

    Vas getVasById(int id);

    List<Vas> getAllVas();

}
