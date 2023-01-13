package com.yoprogramo.backend_portfoliomarlenys.Controller;

import com.yoprogramo.backend_portfoliomarlenys.Dto.DtoProyecto;
import com.yoprogramo.backend_portfoliomarlenys.Entity.Proyecto;
import com.yoprogramo.backend_portfoliomarlenys.Security.Controller.Mensaje;
import com.yoprogramo.backend_portfoliomarlenys.Service.ProyectoService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:4200")//https://portfoliomarlenys.web.app
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = proyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto) {
        if (StringUtils.isBlank(dtoProyecto.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoService.existsByName(dtoProyecto.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = new Proyecto(dtoProyecto.getImage(), dtoProyecto.getName(),
                dtoProyecto.getDescription(), dtoProyecto.getDate(), dtoProyecto.getUrl());
        proyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("Item Registrado con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyecto dtoProyecto) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (proyectoService.existsByName(dtoProyecto.getName())
                && proyectoService.getByName(dtoProyecto.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Item ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoProyecto.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = proyectoService.getOne(id).get();
        proyecto.setImage(dtoProyecto.getImage());
        proyecto.setName(dtoProyecto.getName());
        proyecto.setDescription(dtoProyecto.getDescription());
        proyecto.setDate(dtoProyecto.getDate());
        proyecto.setUrl(dtoProyecto.getUrl());

        proyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("Item Actualizada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proyectoService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        proyectoService.delete(id);
        return new ResponseEntity(new Mensaje("Item eliminado con éxito"), HttpStatus.OK);
    }
}
