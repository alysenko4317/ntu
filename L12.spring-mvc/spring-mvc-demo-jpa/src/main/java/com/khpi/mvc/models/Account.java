package com.khpi.mvc.models;

import com.khpi.mvc.forms.UserForm;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="account")
public class Account
{
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

    // https://www.baeldung.com/jpa-many-to-many
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "car_account_link",
        joinColumns = @JoinColumn(name = "account_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;

    @Transient
    private Integer carsOwned;

    public static Account from(UserForm form) {
        return Account.builder()
            .firstName(form.getFirstName())
            .lastName(form.getLastName())
            .email(form.getLogin())
            .passwordHash(form.getPassword())  // FIXME:  should not store passwords as plain text
            .build();
    }

  /*  @Override
    public String toString() {
        return "Account{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", cars=" + (cars != null ? "array[" + cars.size() + "]": "null") +
            '}';
    }*/
}
