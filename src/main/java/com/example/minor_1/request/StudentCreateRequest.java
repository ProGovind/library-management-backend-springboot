package com.example.minor_1.request;

import com.example.minor_1.models.AccountStatus;
import com.example.minor_1.models.Student;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    private String address;
    private String email;

    public Student to()
    {
        return  Student.builder().
                address(address)
                .contact(contact)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .email(email)
                .build();

    }

}
