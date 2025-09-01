package lk.ijse.cmjd_111.CourseRegistration2025.controller.userRelated;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.impl.LectureServiceIMPL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/lecture")
public class LectureController extends GenericController<UserDTO> {
    public LectureController(LectureServiceIMPL lectureService) {
        super(lectureService);
    }
}