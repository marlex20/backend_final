package com.yoprogramo.backend_portfoliomarlenys.Security.Repository;

import com.yoprogramo.backend_portfoliomarlenys.Security.Entity.Rol;
import com.yoprogramo.backend_portfoliomarlenys.Security.Enum.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
