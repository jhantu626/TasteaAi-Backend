package io.app.dto;

import io.app.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String mobile;
    private Role role;
    private String profilePicture;
    private Date createdAt;
    private Date updatedAt;
}
