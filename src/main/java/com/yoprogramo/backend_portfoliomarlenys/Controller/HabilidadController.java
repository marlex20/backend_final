package com.yoprogramo.backend_portfoliomarlenys.Controller;

import com.yoprogramo.backend_portfoliomarlenys.Dto.DtoHabilidad;
import com.yoprogramo.backend_portfoliomarlenys.Entity.Habilidad;
import com.yoprogramo.backend_portfoliomarlenys.Security.Controller.Mensaje;
import com.yoprogramo.backend_portfoliomarlenys.Service.HabilidadService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habilidad")
@CrossOrigin(origins = "http://localhost:4200")//https://portfoliomarlenys.web.app
public class HabilidadController {

    @Autowired
    HabilidadService habilidadService;

    @GetMapping("/lista")
    public ResponseEntity<List<Habilidad>> list() {
        List<Habilidad> list = habilidadService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        Habilidad habilidad = habilidadService.getOne(id).get();
        return new ResponseEntity(habilidad, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHabilidad dtoHabilidad) {
        if (StringUtils.isBlank(dtoHabilidad.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (habilidadService.existsByName(dtoHabilidad.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = new Habilidad(dtoHabilidad.getName(),
                dtoHabilidad.getVal());
        habilidadService.save(habilidad);
        return new ResponseEntity(new Mensaje("Habilidad Registrada con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHabilidad dtoHabilidad) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (habilidadService.existsByName(dtoHabilidad.getName())
                && habilidadService.getByName(dtoHabilidad.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Item ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoHabilidad.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        Habilidad habilidad = habilidadService.getOne(id).get();
        habilidad.setName(dtoHabilidad.getName());
        habilidad.setVal(dtoHabilidad.getVal());

        habilidadService.save(habilidad);
        return new ResponseEntity(new Mensaje("Item Actualizada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!habilidadService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        habilidadService.delete(id);
        return new ResponseEntity(new Mensaje("Item eliminado con éxito"), HttpStatus.OK);
    }
}
