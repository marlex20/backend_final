package com.yoprogramo.backend_portfoliomarlenys.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Entity.ExperienciaLaboral;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaLaboral extends JpaRepository<ExperienciaLaboral, Integer> {

    public Optional<ExperienciaLaboral> findByName(String name);

    public boolean existsByName(String name);
}
