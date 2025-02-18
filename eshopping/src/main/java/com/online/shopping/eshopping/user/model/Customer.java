package com.online.shopping.eshopping.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "customers",                      // Table name in the default schema
        indexes = {                               // Indexes to improve query performance
                @Index(name = "idx_email", columnList = "email"),
                @Index(name = "idx_name_role", columnList = "name, role")
        },
        uniqueConstraints = {                     // Unique constraints at the table level
                @UniqueConstraint(name = "uc_email", columnNames = "email"),
                @UniqueConstraint(name = "uc_phone", columnNames = "phone")
        }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false) // Ensures not null at the database level
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false) // Ensures unique email and not null in DB
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(unique = true, nullable = false) // Ensures unique phone number and not null in DB
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false) // Ensures password is not null in DB
    private String password;

    @Enumerated(EnumType.STRING) // Store role as a string (USER/ADMIN)
    private Role role;

    public Customer() {}

    public Customer(String name, String email, String phone, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
