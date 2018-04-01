package ua.pp.jjd.lmsnew.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.pp.jjd.lmsnew.dto.StudentDTO;
import ua.pp.jjd.lmsnew.service.StudentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> add(@RequestBody StudentDTO studentDTO) {
        try {
            return ResponseEntity.ok(studentService.add(studentDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO studentDTO) {
        try {
            return ResponseEntity.ok(studentService.update(studentDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}