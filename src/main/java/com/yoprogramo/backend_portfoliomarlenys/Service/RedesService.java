
package com.yoprogramo.backend_portfoliomarlenys.Service;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Redes;
import com.yoprogramo.backend_portfoliomarlenys.Repository.IRedes;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RedesService {
    @Autowired
    IRedes iRedes;

    public List<Redes> list() {
        return iRedes.findAll();
    }

    public Optional<Redes> getOne(int id) {
        return iRedes.findById(id);
    }

    public Optional<Redes> getByName(String name) {
        return iRedes.findByName(name);
    }

    public void save(Redes redes) {
        iRedes.save(redes);
    }

    public void delete(int id) {
        iRedes.deleteById(id);
    }

    public boolean existsById(int id) {
        return iRedes.existsById(id);
    }

    public boolean existsByName(String name) {
        return iRedes.existsByName(name);
    } 
}
