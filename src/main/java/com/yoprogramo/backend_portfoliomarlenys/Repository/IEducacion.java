package com.yoprogramo.backend_portfoliomarlenys.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacion extends JpaRepository<Educacion, Integer> {

    public Optional<Educacion> findByName(String name);

    public boolean existsByName(String name);
}
