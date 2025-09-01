package lk.ijse.cmjd_111.CourseRegistration2025.controller;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.EnrollmentDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.EnrollmentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Void>saveEnrollent(@RequestBody EnrollmentDTO enrollmentDTO){
        enrollmentService.saveEnrollment(enrollmentDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @SneakyThrows
    @GetMapping("{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollment(@PathVariable String id) {
        EnrollmentDTO dto = enrollmentService.getSelectedEnrollment(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return new ResponseEntity<>(enrollmentService.getAllEnrollments(), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public void updateEnrollment(@RequestParam String id, @RequestBody EnrollmentDTO EnrollmentUpdateDto) {
        System.out.print(" Enrollment updated Id " + id);
        System.out.print(EnrollmentUpdateDto);
    }

    @DeleteMapping
    public void deleteEnrollment(@RequestHeader("y-enrollmentId") String id) {
        System.out.print("id is to be deleted "+id);
    }
}

