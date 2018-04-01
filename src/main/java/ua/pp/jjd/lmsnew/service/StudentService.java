package ua.pp.jjd.lmsnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.jjd.lmsnew.domain.Course;
import ua.pp.jjd.lmsnew.domain.Student;
import ua.pp.jjd.lmsnew.dto.StudentDTO;
import ua.pp.jjd.lmsnew.repository.CourseRepository;
import ua.pp.jjd.lmsnew.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public List<StudentDTO> getAll() {
        return studentRepository.findAll().stream()
                .map(this::fromStudent)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDTO getById(Long id) {
        return fromStudent(studentRepository.getOne(id));
    }

    @Transactional
    public StudentDTO add(StudentDTO studentDTO) {
        Student studentSaved = studentRepository.saveAndFlush(fromDTO(studentDTO));
        return fromStudent(studentSaved);
    }

    @Transactional
    public StudentDTO update(StudentDTO studentDTO) {
        Student studentSaved = studentRepository.saveAndFlush(fromDTO(studentDTO));
        return fromStudent(studentSaved);
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO fromStudent(Student student) {
        StudentDTO studentDTO = null;
        if (student != null) {
            studentDTO = StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .courses(student.getCourses() != null
                            ? student.getCourses().stream()
                            .map(Course::getName)
                            .collect(Collectors.toList())
                            : null)
                    .build();
        }
        return studentDTO;
    }

    private Student fromDTO(StudentDTO studentDTO) {
        Student student = null;
        if (studentDTO != null) {
            student = Student.builder()
                    .id(studentDTO.getId())
                    .name(studentDTO.getName())
                    .courses(studentDTO.getCourses() != null
                            ? courseRepository.findAllByName(studentDTO.getCourses())
                            : null)
                    .build();
        }
        return student;
    }

}
