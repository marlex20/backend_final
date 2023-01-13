package com.yoprogramo.backend_portfoliomarlenys.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Entity.Redes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRedes extends JpaRepository<Redes, Integer> {

    public Optional<Redes> findByName(String name);

    public boolean existsByName(String name);
}
