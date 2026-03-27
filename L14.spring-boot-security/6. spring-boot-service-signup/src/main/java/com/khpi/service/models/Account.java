package com.khpi.service.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="account")
public class Account
{
    public enum Role {
        ADMIN, USER
    }

    public enum State {
        ACTIVE, BANNED, DELETED;
    }

    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;

    @Column(name="password")
    private String passwordHash;

    // в БД буде зберігатись як varchar
    @Enumerated(value=EnumType.STRING)
    private Role role;

    @Enumerated(value=EnumType.STRING)
    private State state;

    // https://www.baeldung.com/jpa-many-to-many
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "car_account_link",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
}
