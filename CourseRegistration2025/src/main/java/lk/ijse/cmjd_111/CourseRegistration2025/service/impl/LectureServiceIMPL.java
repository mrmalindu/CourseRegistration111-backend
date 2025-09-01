package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;

import lk.ijse.cmjd_111.CourseRegistration2025.dao.LectureDao;
import lk.ijse.cmjd_111.CourseRegistration2025.entity.LectureEntity;
import lk.ijse.cmjd_111.CourseRegistration2025.util.Conversion;
import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.GenericService;
import lk.ijse.cmjd_111.CourseRegistration2025.util.IDGen;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureServiceIMPL implements GenericService<UserDTO> {
    private final LectureDao lectureDao;
    private final Conversion conversion;

    @Override
    public void saveUser(UserDTO user) {
        var lectureEntity = conversion.toLectureEntity(user);
        lectureEntity.setUserID(IDGen.generateLectureID());
        lectureDao.save(lectureEntity);
    }

    @SneakyThrows
    @Override
    public void deleteUser(String lectureId) {
        Optional<LectureEntity> foundLecture =
            lectureDao.findById(lectureId);
        if(!foundLecture.isPresent()){
            throw new Exception("Lecture not found");
        }
        lectureDao.deleteById(lectureId);
    }

    @SneakyThrows
    @Override
    public void updateUser(String lectureId, UserDTO lecturerToBeUpdated) {
        Optional<LectureEntity> foundLecture =
                lectureDao.findById(lectureId);
        if(!foundLecture.isPresent()){
            throw new Exception("Lecture not found");
        }

    }

    @Override
    public UserDTO getSelectedUser(String lectureId) throws Exception {
        Optional<LectureEntity> foundLecture =lectureDao.findById(lectureId);
        if(foundLecture.isPresent()){
            throw new Exception("Lecture not found");
        }
        return conversion.toLectureDto(lectureDao.getReferenceById(lectureId));
    }

    public List<UserDTO> getAllUsers() {
        return conversion.toLectureDtoList(lectureDao.findAll());
    }
}