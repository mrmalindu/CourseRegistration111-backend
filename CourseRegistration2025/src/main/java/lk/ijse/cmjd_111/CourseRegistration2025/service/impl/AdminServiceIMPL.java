package lk.ijse.cmjd_111.CourseRegistration2025.service.impl;

import lk.ijse.cmjd_111.CourseRegistration2025.dto.UserDTO;
import lk.ijse.cmjd_111.CourseRegistration2025.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceIMPL implements GenericService<UserDTO> {
    /**
     * @param user
     */
    @Override
    public void saveUser(UserDTO user) {

    }

    /**
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {

    }

    /**
     * @param userId
     * @param user
     */
    @Override
    public void updateUser(String userId, UserDTO user) {

    }

    /**
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO getSelectedUser(String userId) throws Exception {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }
}

