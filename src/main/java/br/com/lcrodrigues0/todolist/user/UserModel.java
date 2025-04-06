package br.com.lcrodrigues0.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data                       // Lombok annotation to automatically create getters and setters
@Entity(name = "tb_users")  // JPA annotation to create a table at database
public class UserModel {
    @Id                                     // JPA annotation to identify this attribute as a primary key 
    @GeneratedValue(generator = "UUID")     // JPA annotation to automatically generate UUID
    private UUID id;                        // UUID is a java class to generate and work with universal unique identifiers. A random identifier is created.


    // @Column(name = "user")      // JPA annotation to change the name of the column associated with this atributte
    private String username;
    private String name;
    private String password;

    @CreationTimestamp          // JPA annotation to automatically provide the timestamp to the database
    private LocalDateTime createdAt;
}
