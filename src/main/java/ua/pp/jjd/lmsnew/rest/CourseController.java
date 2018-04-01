package ua.pp.jjd.lmsnew.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.jjd.lmsnew.dto.CourseDTO;
import ua.pp.jjd.lmsnew.service.CourseService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(courseService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CourseDTO> add(@RequestBody CourseDTO courseDTO) {
        try {
            return ResponseEntity.ok(courseService.add(courseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<CourseDTO> update(@RequestBody CourseDTO courseDTO) {
        try {
            return ResponseEntity.ok(courseService.update(courseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
