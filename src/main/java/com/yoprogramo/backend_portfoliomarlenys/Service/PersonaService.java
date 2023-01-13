package com.yoprogramo.backend_portfoliomarlenys.Service;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Persona;
import com.yoprogramo.backend_portfoliomarlenys.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {

    @Autowired
    IPersonaRepository iPersonaRepository;

    public List<Persona> list() {
        return iPersonaRepository.findAll();
    }

    public Optional<Persona> getOne(int id) {
        return iPersonaRepository.findById(id);
    }

    public Optional<Persona> getByName(String name) {
        return iPersonaRepository.findByName(name);
    }

    public void save(Persona persona) {
        iPersonaRepository.save(persona);
    }

    public void delete(int id) {
        iPersonaRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iPersonaRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return iPersonaRepository.existsByName(name);
    }

    public List<Persona> getPersona() {
        List<Persona> persona = iPersonaRepository.findAll();
        return persona;
    }

    public Persona findPersona(int id) {
        Persona persona = iPersonaRepository.findById(id).orElse(null);
        return persona;
    }
}
