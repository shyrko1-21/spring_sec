package com.qcompany.qms.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty(message = "{FormField.isEmpty}")
    @Column(name = "login", unique = true)
    private String login;

    @NotEmpty(message = "{FormField.isEmpty}")
    @Size(min=5)
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "{FormField.isEmpty}")
    @Column(name = "full_name")
    private String fullName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="branch_id")
    private Branch branch;

    @Column(name = "is_Active")
    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String login, String password, String fullName, boolean active) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.active = active;
    }
}
