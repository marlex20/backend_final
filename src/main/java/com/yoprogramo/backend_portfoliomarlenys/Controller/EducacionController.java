package com.yoprogramo.backend_portfoliomarlenys.Controller;

import com.yoprogramo.backend_portfoliomarlenys.Dto.DtoEducacion;
import com.yoprogramo.backend_portfoliomarlenys.Entity.Educacion;
import com.yoprogramo.backend_portfoliomarlenys.Security.Controller.Mensaje;
import com.yoprogramo.backend_portfoliomarlenys.Service.EducacionService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://marlenysportfolio.web.app/")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion) {
        if (StringUtils.isBlank(dtoEducacion.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (educacionService.existsByName(dtoEducacion.getName())) {
            return new ResponseEntity(new Mensaje("Item no registrado"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEducacion.getImage(),
                dtoEducacion.getName(), dtoEducacion.getTitle(),
                dtoEducacion.getDescription(), dtoEducacion.getDate());
        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Experiencia Registrada con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEducacion) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (educacionService.existsByName(dtoEducacion.getName())
                && educacionService.getByName(dtoEducacion.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Item ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEducacion.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educacionService.getOne(id).get();
        educacion.setImage(dtoEducacion.getImage());
        educacion.setName(dtoEducacion.getName());
        educacion.setTitle(dtoEducacion.getTitle());
        educacion.setDescription(dtoEducacion.getDescription());
        educacion.setDate(dtoEducacion.getDate());

        educacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Item Actualizada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educacionService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        educacionService.delete(id);
        return new ResponseEntity(new Mensaje("Item eliminado con éxito"), HttpStatus.OK);
    }
}
