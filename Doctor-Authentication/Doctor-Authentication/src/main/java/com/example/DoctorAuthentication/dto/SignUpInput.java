package com.example.DoctorAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userContact;

}
