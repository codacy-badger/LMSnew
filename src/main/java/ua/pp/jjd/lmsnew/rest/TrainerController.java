package ua.pp.jjd.lmsnew.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.jjd.lmsnew.dto.TrainerDTO;
import ua.pp.jjd.lmsnew.service.TrainerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public List<TrainerDTO> getAll() {
        return trainerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(trainerService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> add(@RequestBody TrainerDTO trainerDTO) {
        try {
            return ResponseEntity.ok(trainerService.add(trainerDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            trainerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<TrainerDTO> update(@RequestBody TrainerDTO trainerDTO) {
        try {
            return ResponseEntity.ok(trainerService.update(trainerDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}