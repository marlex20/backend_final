package com.yoprogramo.backend_portfoliomarlenys.Service;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Habilidad;
import com.yoprogramo.backend_portfoliomarlenys.Repository.IHabilidad;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HabilidadService {

    @Autowired
    IHabilidad iHabilidad;

    public List<Habilidad> list() {
        return iHabilidad.findAll();
    }

    public Optional<Habilidad> getOne(int id) {
        return iHabilidad.findById(id);
    }

    public Optional<Habilidad> getByName(String name) {
        return iHabilidad.findByName(name);
    }

    public void save(Habilidad habilidad) {
        iHabilidad.save(habilidad);
    }

    public void delete(int id) {
        iHabilidad.deleteById(id);
    }

    public boolean existsById(int id) {
        return iHabilidad.existsById(id);
    }

    public boolean existsByName(String name) {
        return iHabilidad.existsByName(name);
    }
}
