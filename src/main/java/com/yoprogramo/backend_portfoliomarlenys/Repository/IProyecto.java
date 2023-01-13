package com.yoprogramo.backend_portfoliomarlenys.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Proyecto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyecto extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByName(String name);

    public boolean existsByName(String name);
}
