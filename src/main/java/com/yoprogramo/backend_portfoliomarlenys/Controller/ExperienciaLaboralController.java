package com.yoprogramo.backend_portfoliomarlenys.Controller;

import com.yoprogramo.backend_portfoliomarlenys.Dto.DtoExperienciaLaboral;
import com.yoprogramo.backend_portfoliomarlenys.Entity.ExperienciaLaboral;
import com.yoprogramo.backend_portfoliomarlenys.Security.Controller.Mensaje;
import com.yoprogramo.backend_portfoliomarlenys.Service.ExperienciaLaboralService;
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
@RequestMapping("/experienciaLaboral")
@CrossOrigin(origins = "https://marlenysportfolio.web.app/")
public class ExperienciaLaboralController {

    @Autowired
    ExperienciaLaboralService experienciaLaboralService;

    @GetMapping("/lista")
    public ResponseEntity<List<ExperienciaLaboral>> list() {
        List<ExperienciaLaboral> list = experienciaLaboralService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ExperienciaLaboral> getById(@PathVariable("id") int id) {
        if (!experienciaLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Esta Experiencia laboral no existe"), HttpStatus.NOT_FOUND);
        }
        ExperienciaLaboral experienciaLaboral = experienciaLaboralService.getOne(id).get();
        return new ResponseEntity(experienciaLaboral, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperienciaLaboral dtoExperienciaLaboral) {
        if (StringUtils.isBlank(dtoExperienciaLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaLaboralService.existsByName(dtoExperienciaLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Experiencia no registrada"), HttpStatus.BAD_REQUEST);
        }

        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral(dtoExperienciaLaboral.getImage(),
                dtoExperienciaLaboral.getName(), dtoExperienciaLaboral.getTitle(),
                dtoExperienciaLaboral.getDescription(), dtoExperienciaLaboral.getDate());
        experienciaLaboralService.save(experienciaLaboral);
        return new ResponseEntity(new Mensaje("Experiencia Registrada con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperienciaLaboral dtoExperienciaLaboral) {
        if (!experienciaLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (experienciaLaboralService.existsByName(dtoExperienciaLaboral.getName())
                && experienciaLaboralService.getByName(dtoExperienciaLaboral.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoExperienciaLaboral.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        ExperienciaLaboral experienciaLaboral = experienciaLaboralService.getOne(id).get();
        experienciaLaboral.setImage(dtoExperienciaLaboral.getImage());
        experienciaLaboral.setName(dtoExperienciaLaboral.getName());
        experienciaLaboral.setTitle(dtoExperienciaLaboral.getTitle());
        experienciaLaboral.setDescription(dtoExperienciaLaboral.getDescription());
        experienciaLaboral.setDate(dtoExperienciaLaboral.getDate());

        experienciaLaboralService.save(experienciaLaboral);
        return new ResponseEntity(new Mensaje("Experiencia Laboral Actualizada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!experienciaLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        experienciaLaboralService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada con éxito"), HttpStatus.OK);
    }
}
