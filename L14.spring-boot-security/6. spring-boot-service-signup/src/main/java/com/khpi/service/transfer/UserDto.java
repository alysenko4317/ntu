package com.khpi.service.transfer;

import com.khpi.service.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// за своєю суттю - це view model (утримує обмежені дані, тільки ті які потрібні на формі)

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;

    // фабричний метод - створює цей ДТО з моделі користувача
    public static UserDto from(Account user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
