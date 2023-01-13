package com.yoprogramo.backend_portfoliomarlenys.Service;

import com.yoprogramo.backend_portfoliomarlenys.Entity.ExperienciaLaboral;
import com.yoprogramo.backend_portfoliomarlenys.Repository.IExperienciaLaboral;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaLaboralService {

    @Autowired
    IExperienciaLaboral iExperienciaLaboral;

    public List<ExperienciaLaboral> list() {
        return iExperienciaLaboral.findAll();
    }

    public Optional<ExperienciaLaboral> getOne(int id) {
        return iExperienciaLaboral.findById(id);
    }

    public Optional<ExperienciaLaboral> getByName(String name) {
        return iExperienciaLaboral.findByName(name);
    }

    public void save(ExperienciaLaboral experienciaLaboral) {
        iExperienciaLaboral.save(experienciaLaboral);
    }

    public void delete(int id) {
        iExperienciaLaboral.deleteById(id);
    }

    public boolean existsById(int id) {
        return iExperienciaLaboral.existsById(id);
    }

    public boolean existsByName(String name) {
        return iExperienciaLaboral.existsByName(name);
    }
}
