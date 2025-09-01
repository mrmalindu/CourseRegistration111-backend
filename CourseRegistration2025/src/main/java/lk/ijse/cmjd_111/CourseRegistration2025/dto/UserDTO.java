package lk.ijse.cmjd_111.CourseRegistration2025.dto;

import lk.ijse.cmjd_111.CourseRegistration2025.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable
{
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String password;
    private Role role;
}
