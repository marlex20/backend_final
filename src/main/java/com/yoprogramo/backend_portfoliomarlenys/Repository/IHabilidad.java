package com.yoprogramo.backend_portfoliomarlenys.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHabilidad extends JpaRepository<Habilidad, Integer> {

    public Optional<Habilidad> findByName(String name);

    public boolean existsByName(String name);
}
