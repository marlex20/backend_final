package com.yoprogramo.backend_portfoliomarlenys.Controller;

import com.yoprogramo.backend_portfoliomarlenys.Dto.DtoRedes;
import com.yoprogramo.backend_portfoliomarlenys.Entity.Redes;
import com.yoprogramo.backend_portfoliomarlenys.Security.Controller.Mensaje;
import com.yoprogramo.backend_portfoliomarlenys.Service.RedesService;
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
@RequestMapping("/redes")
@CrossOrigin(origins = "http://localhost:4200")//https://portfoliomarlenys.web.app
public class RedesController {

    @Autowired
    RedesService redesService;

    @GetMapping("/lista")
    public ResponseEntity<List<Redes>> list() {
        List<Redes> list = redesService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Redes> getById(@PathVariable("id") int id) {
        if (!redesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este item no existe"), HttpStatus.NOT_FOUND);
        }
        Redes redes = redesService.getOne(id).get();
        return new ResponseEntity(redes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoRedes dtoRedes) {
        if (StringUtils.isBlank(dtoRedes.getName())) {
            return new ResponseEntity(new Mensaje("Debe incluir nombre"), HttpStatus.BAD_REQUEST);
        }
        if (redesService.existsByName(dtoRedes.getName())) {
            return new ResponseEntity(new Mensaje("Item repetido"), HttpStatus.BAD_REQUEST);
        }

        Redes redes = new Redes(dtoRedes.getName(), dtoRedes.getLogo());
        redesService.save(redes);
        return new ResponseEntity(new Mensaje("Item Registrado con éxito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoRedes dtoRedes) {
        if (!redesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (redesService.existsByName(dtoRedes.getName())
                && redesService.getByName(dtoRedes.getName()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Item ya existente"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoRedes.getName())) {
            return new ResponseEntity(new Mensaje("Nombre requerido"), HttpStatus.BAD_REQUEST);
        }

        Redes redes = redesService.getOne(id).get();
        redes.setName(dtoRedes.getName());
        redes.setLogo(dtoRedes.getLogo());

        redesService.save(redes);
        return new ResponseEntity(new Mensaje("Item Actualizada con éxito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!redesService.existsById(id)) {
            return new ResponseEntity(new Mensaje("Este Id no Existe"), HttpStatus.NOT_FOUND);
        }
        redesService.delete(id);
        return new ResponseEntity(new Mensaje("Item eliminado con éxito"), HttpStatus.OK);
    }
}
