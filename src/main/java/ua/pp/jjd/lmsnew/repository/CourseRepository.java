package ua.pp.jjd.lmsnew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.jjd.lmsnew.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
