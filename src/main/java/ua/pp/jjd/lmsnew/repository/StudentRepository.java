package ua.pp.jjd.lmsnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.jjd.lmsnew.domain.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByName(List<String> names);

}
