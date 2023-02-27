package com.khpi.service.transfer;

import lombok.*;
import com.khpi.service.models.Account;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    private String login;

    public static UserDto from(Account user) {
        return UserDto.builder()
                 .id(user.getPk())
                 .login(user.getEmail())
                 .build();
    }

    public static List<UserDto> from(List<Account> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
