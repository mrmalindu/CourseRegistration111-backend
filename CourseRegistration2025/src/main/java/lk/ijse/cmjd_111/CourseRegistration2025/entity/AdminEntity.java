package lk.ijse.cmjd_111.CourseRegistration2025.entity;

import jakarta.persistence.*;
import lk.ijse.cmjd_111.CourseRegistration2025.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin")

public class AdminEntity implements Serializable {
    @Id
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
