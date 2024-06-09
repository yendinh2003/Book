package com.bookstore.entity;

import com.bookstore.validator.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "username không được phép rỗng")
    @Size(max = 50, message = "username không vượt quá 50 ký tự")
    @ValidUsername
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "password không được phép rỗng")
    private String password;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "email không vượt quá 50 ký tự")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @NotBlank(message = "name không được phép rỗng")
    @Size(max = 50, message = "name không vượt quá 50 ký tự")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Book> books;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
