package com.devdiaries.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

/**
 * User entity representing a Dev-Diaries user
 */
@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String username;

    private String password; // hashed password

    private String firstName;

    private String lastName;

    private boolean emailVerified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean active;

}
