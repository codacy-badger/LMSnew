package ua.pp.jjd.lmsnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.jjd.lmsnew.domain.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

//    Course getCourseByName(String name);

    List<Course> findAllByName(List<String> names);

}
