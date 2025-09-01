package lk.ijse.cmjd_111.CourseRegistration2025.util;

import lk.ijse.cmjd_111.CourseRegistration2025.dao.CourseDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.CourseMaterialDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.LectureDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dao.StudentDao;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.CourseMaterialDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Conversion {
    private final ModelMapper modelMapper;
    private final LectureDao lectureDao;
    private final CourseDao courseDao;
    private final CourseMaterialDao courseMaterialDao;
    private final StudentDao studentDao;

    //Student
    public UserDTO toStudentDto(StudentEntity student){
        return modelMapper.map(student, UserDTO.class);
    }
    public StudentEntity toStudentEntity(UserDTO userDto){
        return modelMapper.map(userDto,StudentEntity.class);
    }
    public List<UserDTO> toStudentDtoList(List<StudentEntity> students){
        return modelMapper.map(students,new TypeToken<List<UserDTO>>(){}.getType());
    }
    public UserDTO toLectureDto(LectureEntity lecture){
        return modelMapper.map(lecture, UserDTO.class);
    }
    public LectureEntity toLectureEntity(UserDTO userDto){
        return modelMapper.map(userDto,LectureEntity.class);
    }
    public List<UserDTO> toLectureDtoList(List<LectureEntity> lectures){
        return modelMapper.map(lectures,new TypeToken<List<UserDTO>>(){}.getType());
    }


    //Course
    public CourseDTO toCourseDTO(CourseEntity course){
        var courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseCode(course.getCourseCode());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCredit(course.getCredit());
        courseDTO.setStartTime(course.getStartTime());
        courseDTO.setEndTime(course.getEndTime());
        if(course.getLecturer() !=null){
            courseDTO.setLecturer(course.getLecturer().getUserID());
        }
        return courseDTO;
    }

    public CourseEntity toCourseEntity(CourseDTO courseDTO){
        var courseEntity = new CourseEntity();
        courseEntity.setCourseId(courseDTO.getCourseId());
        courseEntity.setCourseName(courseDTO.getCourseName());
        courseEntity.setCourseCode(courseDTO.getCourseCode());
        courseEntity.setDescription(courseDTO.getDescription());
        courseEntity.setCredit(courseDTO.getCredit());
        courseEntity.setStartTime(courseDTO.getStartTime());
        courseEntity.setEndTime(courseDTO.getEndTime());
        LectureEntity lecturer= lectureDao.findById(courseDTO.getLecturer())
                .orElseThrow(()-> new RuntimeException("Lecturer Not Found"));
        courseEntity.setLecturer(lecturer);
        return courseEntity;
    }
    public List<CourseDTO> toCourseDTOList(List<CourseEntity> courses){
        return courses.stream().map(this::toCourseDTO).toList();
    }
    public CourseMaterialDTO toCourseMaterialDTO(CourseMaterialEntity courseMaterialEntity) {
        CourseMaterialDTO dto = new CourseMaterialDTO();
        dto.setMaterialId(courseMaterialEntity.getMaterialId());
        dto.setFileName(courseMaterialEntity.getFileName());
        dto.setMaterialType(courseMaterialEntity.getMaterialType());
        dto.setMaterial(courseMaterialEntity.getMaterial());
        dto.setUploadAt(courseMaterialEntity.getUploadAt());
        if (courseMaterialEntity.getCourse() != null) {
            dto.setCourseId(courseMaterialEntity.getCourse().getCourseId());
        }
        return dto;

    }
    public CourseMaterialEntity toCourseMaterialEntity(CourseMaterialDTO courseMaterialDTO) {
        CourseMaterialEntity entity = new CourseMaterialEntity();
        entity.setMaterialId(courseMaterialDTO.getMaterialId());
        entity.setFileName(courseMaterialDTO.getFileName());
        entity.setMaterialType(courseMaterialDTO.getMaterialType());
        entity.setMaterial(courseMaterialDTO.getMaterial());
        entity.setUploadAt(courseMaterialDTO.getUploadAt());

        if (courseMaterialDTO.getCourseId() != null) {
            CourseEntity selectedCourse = courseDao.findById(courseMaterialDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course Material not found with id: " + courseMaterialDTO.getCourseId()));
            entity.setCourse(selectedCourse);
        }

        return entity;
    }
    public List<CourseMaterialDTO> toCourseMaterialDTOList(List<CourseMaterialEntity> entities) {
        return entities.stream().map(this::toCourseMaterialDTO).toList();
    }

    public List<CourseMaterialEntity> toCourseMaterialEntityList(List<CourseMaterialDTO> dtos) {
        return dtos.stream().map(this::toCourseMaterialEntity).toList();
    }
    public EnrollmentDTO toCourseEnrollmentDTO(EnrollmentEntity enrollmentlEntity) {
        var enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setMarks(enrollmentlEntity.getMarks());
        enrollmentDTO.setGrade(enrollmentlEntity.getGrade());
        enrollmentDTO.setEnrollmentDate(enrollmentlEntity.getEnrollmentDate());
        if (enrollmentlEntity.getCourse() != null) {
            enrollmentDTO.setCourseId(enrollmentlEntity.getCourse().getCourseId());
        }
        if(enrollmentlEntity.getStudent() != null){
            enrollmentDTO.setStudentId(enrollmentlEntity.getStudent().getUserID());
        }
        return enrollmentDTO;
    }
    public EnrollmentEntity toCourseEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        var enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setMarks(enrollmentDTO.getMarks());
        enrollmentEntity.setGrade(enrollmentDTO.getGrade());
        enrollmentDTO.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        if (enrollmentDTO.getCourseId() != null) {
            CourseEntity selectedCourse = courseDao.findById(enrollmentDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + enrollmentDTO.getCourseId()));
            enrollmentEntity.setCourse(selectedCourse);
        }
        if (enrollmentDTO.getStudentId() != null) {
            StudentEntity selecteStudent = studentDao.findById(enrollmentDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + enrollmentDTO.getCourseId()));
            enrollmentEntity.setStudent(selecteStudent);
        }
        return enrollmentEntity;
    }
    public List<EnrollmentDTO> toCourseEnrollmentDTOList(List<EnrollmentEntity> enrollmentEntities) {
        return enrollmentEntities.stream().map(this::toCourseEnrollmentDTO).toList();
    }

    public List<EnrollmentEntity> toCourseEnrollmentlEntityList(List<EnrollmentDTO> dtos) {
        return dtos.stream().map(this::toCourseEnrollmentEntity).toList();
    }

}
