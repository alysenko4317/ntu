package com.khpi.service.transfer;

import com.khpi.service.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// view model

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;

    public static UserDto from(Account user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
