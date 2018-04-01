package ua.pp.jjd.lmsnew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.jjd.lmsnew.domain.Course;
import ua.pp.jjd.lmsnew.domain.Trainer;
import ua.pp.jjd.lmsnew.dto.CourseDTO;
import ua.pp.jjd.lmsnew.repository.CourseRepository;
import ua.pp.jjd.lmsnew.repository.TrainerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseService {

    private final CourseRepository courseRepository;
    private final TrainerRepository trainerRepository;

    @Transactional
    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream()
                .map(this::fromCourse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDTO getById(Long id) {
        return fromCourse(courseRepository.getOne(id));
    }

    @Transactional
    public CourseDTO add(CourseDTO courseDTO) {
        Course courseSaved = courseRepository.saveAndFlush(fromDTO(courseDTO));
        return fromCourse(courseSaved);
    }

    @Transactional
    public CourseDTO update(CourseDTO courseDTO) {
        Course courseSaved = courseRepository.saveAndFlush(fromDTO(courseDTO));
        return fromCourse(courseSaved);
    }

    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO fromCourse(Course course) {
        CourseDTO courseDTO = null;
        if (course != null) {
            courseDTO = CourseDTO.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .startDate(course.getStartDate().toString())
                    .endDate(course.getEndDate().toString())
                    .trainer(course.getTrainer() != null
                            ? course.getTrainer().getName()
                            : null)
                    .build();
        }
        return courseDTO;
    }

    private Course fromDTO(CourseDTO courseDTO) {
        Course course = null;
        if (courseDTO != null) {
            course = Course.builder()
                    .id(courseDTO.getId())
                    .name(courseDTO.getName())
                    .startDate(LocalDate.parse(courseDTO.getStartDate()))
                    .endDate(LocalDate.parse(courseDTO.getEndDate()))
                    .trainer(courseDTO.getTrainer() != null
                            ? trainerRepository.findTrainerByName(courseDTO.getTrainer())
                            : null)
                    .build();
        }
        return course;
    }

}
