package com.khpi.mvc.models;

import com.khpi.mvc.forms.UserForm;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account
{
    private Integer pk;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private List<Car> cars;

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
